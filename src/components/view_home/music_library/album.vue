<template>
  <div class="album">
    <ul ref="getboxwidth" :class="{close_album_box:show_album_content}" class="album_box">
      <li @click="check_album" ref="getliwidth" v-for="item in album" :key="item">
        <img src :onerror="defaultImg" />
        <SvgIcon class="box_play" name="play" />
        <p>合辑名称</p>
      </li>
      <li v-for="item in complement" :key="item+'a'"></li>
    </ul>
    <div ref="album_content" :class="{show_album_content:show_album_content}" class="album_content">
      <div class="album_particularss">
        <album_particulars />
      </div>
      <div class="presentations">
        <presentation />
      </div>
    </div>
  </div>
</template>

<script>
import presentation from "../music_library/presentation";
import album_particulars from "../music_library/album/album_particulars";
export default {
  components: {
    presentation,
    album_particulars,
  },
  props: {
    isback: { type: Boolean },
  },
  data() {
    return {
      defaultImg:
        'this.src="' + require("../../../assets/image/default.jpg") + '"',
      album: 23,
      complement: 0,
      show_album_content: false,
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
    check_album() {
      this.show_album_content = !this.show_album_content;
      this.$emit("isbacks", true);
      window.removeEventListener("resize", this.set_complement);
    },
  },
  watch: {
    isback: function () {
      this.show_album_content = !this.show_album_content;
      window.addEventListener("resize", this.set_complement);
    },
  },
  mounted() {
    this.set_complement();
    window.addEventListener("resize", this.set_complement);
  },
  destroyed() {
    window.removeEventListener("resize", this.set_complement);
    this.$emit("isbacks", false);
  },
};
</script>

<style scoped>
.album {
  width: 100%;
  height: 100%;
}

.album_box {
  width: 100%;
  display: flex;
  flex-wrap: wrap;
  justify-content: space-around;
  padding: 10px 0;
  background-color: rgba(10, 10, 10, 0.3);
}

.album_box li {
  width: 220px;
  text-align: center;
  position: relative;
}

.album_box li img {
  width: 200px;
  height: 150px;
  border-radius: 10px;
  margin: 10px;
  box-shadow: 1px 0px 10px 3px black;
}

.box_play {
  position: absolute;
  bottom: 40px;
  right: 20px;
}

.album_content {
  width: 100%;
  height: 100%;
  display: none;
}

.close_album_box {
  display: none;
}

.show_album_content {
  display: inherit;
}

.album_particularss {
  width: 100%;
  height: 30%;
  background-color: rgba(10, 10, 10, 0.3);
  border-bottom: 1px solid gainsboro;
}

.presentations {
  width: 100%;
  height: calc(70% - 2px);
  overflow: auto;
}

.presentations::-webkit-scrollbar {
  display: none;
}

</style>
