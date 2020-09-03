<template>
  <div class="new_music">
    <ul class="line_box">
      <li @click="setmusic(item)" v-for="(item,index) in music" :key="index">
        <div class="coverimg">
          <img :src="item.img_url" :onerror="defaultImg" />
        </div>
        <div class="content">
          <div>
            {{item.musicname}}
            <span> - {{item.songname}}</span>
            <span class="times">00:00</span>
          </div>
          <div>{{item.albumname}}</div>
        </div>
      </li>
    </ul>
  </div>
</template>

<script scoped>
export default {
  props: {
    setdata: "",
  },
  data() {
    return {
      defaultImg:
        'this.src="' + require("../../../assets/image/default.jpg") + '"',
      music: 20,
    };
  },
  methods:{
    getalbumMusic(id){
      this.$fetchPost("/prmusic/user/getalbummmusic",
      {
        album_id: JSON.stringify(1)
      }).then((res) =>{
        this.music = res.message;
      })
    },
    setmusic(obj){
      this.$emit("getmusic",obj);
    }
  },
  watch:{
    setdata(id){
      this.getalbumMusic(id);
    }
  }
};
</script>

<style scoped>
.new_music {
  width: 100%;
  height: 100%;
}

.line_box li {
  width: 100%;
  height: 50px;
  background-color: rgba(10, 10, 10, 0.3);
  cursor: default;
}

.line_box li:hover {
  background-color: rgba(184, 184, 184, 0.3);
}

.coverimg {
  display: inline-block;
  width: 70px;
  height: 50px;
  margin-left: 5px;
}

.coverimg img {
  display: inline-block;
  width: 100%;
  height: 40px;
  border-radius: 5px;
  margin: 5px 0;
}

.content {
  width: calc(100% - 90px);
  display: inline-block;
  margin-bottom: 5px;
  margin-left: 10px;
  white-space: nowrap;
  overflow: hidden;
}

.times {
  float: right;
  margin-top: 10px;
}
</style>
