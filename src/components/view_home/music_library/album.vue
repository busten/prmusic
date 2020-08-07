<template>
  <div class="album">
    <ul ref="getboxwidth" class="box">
      <li ref="getliwidth" v-for="item in album" :key="item">
        <img src :onerror="defaultImg" />
        <SvgIcon class="box_play" name="play" />
        <p>合辑名称</p>
      </li>
      <li class="lastnum" v-for="item in complement" :key="item+'a'"></li>
    </ul>
  </div>
</template>

<script>
export default {
  data() {
    return {
      defaultImg:
        'this.src="' + require("../../../assets/image/default.jpg") + '"',
      album: 23,
      complement: 0,
    };
  },
  methods: {
    set_complement() {
      let getboxwidth = this.$refs.getboxwidth.clientWidth;
      let getliwidth = this.$refs.getliwidth[0].clientWidth;
      let columns = parseInt(getboxwidth / getliwidth);
      let nums = this.album / columns;
      let rows =
        this.album % columns == 0 ? nums : parseInt(this.album / columns) + 1;
      this.complement = columns * rows - this.album;
    },
  },
  mounted() {
    this.set_complement();
    window.addEventListener('resize', this.set_complement);
  },
  destroyed(){
    window.removeEventListener("resize", this.set_complement);
  }
};
</script>

<style scoped>
.album {
  width: 100%;
}

.box {
  width: 100%;
  display: flex;
  flex-wrap: wrap;
  justify-content: space-around;
  padding: 10px 0;
  background-color: rgba(10,10,10,0.3);
}

.box li {
  width: 220px;
  text-align: center;
  position: relative;
}

.box li img {
  width: 200px;
  height: 150px;
  border-radius: 10px;
  margin: 10px;
  box-shadow: 1px 0px 10px 3px black;
}

.box_play{
  position: absolute;
  bottom: 40px;
  right: 20px;
}
</style>
