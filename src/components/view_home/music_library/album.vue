<template>
  <div class="album">
    <ul ref="getboxwidth" :class="{close_album_box:show_album_content}" class="album_box">
      <li ref="getliwidth" v-for="(item,index) in setalbum" :key="index">
        <img @click="check_album(item)" :src="item.img_url" :onerror="defaultImg" />
        <div @click="check_album(item)" class="prettify"></div>
        <div @click="playalbum(item)">
          <SvgIcon class="box_play" name="play" />
        </div>
        <p>{{item.albumname}}</p>
      </li>
      <li v-for="item in complement" :key="item+'a'"></li>
    </ul>
    <div ref="album_content" :class="{show_album_content:show_album_content}" class="album_content">
      <div class="album_particularss">
        <album_particulars :setalbum="setalbums" />
      </div>
      <div class="presentations">
        <presentation :setdata="setdata" @getmusic="getmusic" />
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
    setalbum: "",
  },
  data() {
    return {
      defaultImg:
        'this.src="' + require("../../../assets/image/default.jpg") + '"',
      album: 0,
      setalbums: "",
      setdata: "",
      complement: 0,
      show_album_content: false,
    };
  },
  methods: {
    playalbum(obj) {
      this.$emit("getalbum", obj);
    },
    getmusic(obj) {
      this.$emit("getmusic", obj);
    },
    set_complement() {
      let getboxwidth = this.$refs.getboxwidth.clientWidth;
      let getliwidth = this.$refs.getliwidth[0].clientWidth + 10;
      let columns = parseInt(getboxwidth / getliwidth);
      let nums = this.album / columns;
      let rows =
        this.album % columns == 0 ? nums : parseInt(this.album / columns) + 1;
      this.complement = columns * rows - this.album;
    },
    check_album(obj) {
      this.show_album_content = !this.show_album_content;
      this.$emit("isbacks", true);
      this.setalbums = obj;
      this.setdata = obj.album_id;
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
  width: 260px;
  height: 200px;
  text-align: center;
  position: relative;
  margin-top: 10px;
}

.prettify {
  position: absolute;
  width: 240px;
  height: 40%;
  bottom: 40px;
  left: 10px;
  background-image: linear-gradient(to top, black, transparent);
  border-radius: 0 0 5px 5px;
  z-index: 1;
}

.album_box li img {
  position: absolute;
  top: 0;
  left: 0;
  width: 240px;
  height: 150px;
  border-radius: 10px;
  margin: 10px;
  box-shadow: 1px 0px 10px 3px black;
}

.album_box li p {
  position: absolute;
  bottom: 10px;
  left: 10px;
}

.box_play {
  position: absolute;
  bottom: 50px;
  right: 10px;
  font-size: 25px;
  z-index: 2;
  opacity: 0.5;
  transition: all 0.3s ease;
}

.box_play:hover {
  font-size: 30px;
  opacity: 1;
  transition: all 0.3s ease;
}

.album_content {
  width: 100%;
  height: calc(100% - 30px);
  text-align: left;
  margin: auto;
  overflow: hidden;
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
  background-color: rgba(10, 10, 10, 0.3);
  border-bottom: 1px solid gainsboro;
  margin-left: 10px;
  min-height: 93px;
}

.presentations {
  width: 100%;
  height: calc(100% - 279px);
  overflow: auto;
}

.presentations::-webkit-scrollbar {
  display: none;
}

@media only screen and (max-width: 850px) {
  .album_box li {
    width: 180px;
  }
  .prettify {
    width: 160px;
    left: 10px;
    bottom: 80px;
  }

  .album_box li p {
    position: absolute;
    bottom: 50px;
    left: 10px;
  }

  .box_play{
    bottom: 85px;
  }
  .album_box li img {
    width: 160px;
    height: 100px;
  }
  .album_particularss {
    height: auto;
  }

  .presentations {
  width: 100%;
  height: calc(100% - 98px);
  overflow: auto;
}
}
</style>
