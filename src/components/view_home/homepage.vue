<template>
  <div class="homepage">
    <van-overlay :show="showthepage" @click="show = false">
      <div class="wrapper" @click.stop>
        <van-loading color="white"/>
      </div>
    </van-overlay>
    <div v-show="!showablbum" class="homepage_header">
      <p class="title">精选合辑</p>
      <div class="box">
        <div class="homepage_header_content" v-for="(item,index) in album.selected" :key="index">
          <ul>
            <li>
              <img @click="goinalbum(item)" :src="item.img_url" :onerror="defaultImg" />
              <div @click="goinalbum(item)" class="prettify"></div>
              <div @click="playalbum(item)">
                <SvgIcon class="box_play" name="play" />
              </div>
            </li>
            <li class="describe">{{item.albumname}}</li>
          </ul>
        </div>
      </div>
    </div>
    <div v-show="!showablbum" class="homepage_list1">
      <p class="title list1_title">合辑推荐</p>
      <div @mouseleave="moveoutinbox" @mouseenter="moveinbox" class="box">
        <div ref="previous" @click="previous" :style="{opacity: turnpage}" class="svgs previous">
          <SvgIcon class="icon" name="previous" />
        </div>
        <div ref="list_box" class="scroll">
          <div
            class="homepage_header_content list1_content"
            v-for="(item,index) in album.recommend"
            :key="index"
          >
            <ul>
              <li>
                <img @click="goinalbum(item)" :src="item.img_url" :onerror="defaultImg" />
                <div @click="goinalbum(item)" class="prettify"></div>
                <div @click="playalbum(item)">
                  <SvgIcon class="box_play" name="play" />
                </div>
              </li>
              <li @click="goinalbum(item)" class="describe">{{item.albumname}}</li>
            </ul>
          </div>
        </div>
        <div ref="nextdown" @click="nextdown" :style="{opacity: turnpage}" class="svgs nextdown">
          <SvgIcon class="icon" name="nextdown" />
        </div>
      </div>
    </div>
    <div v-show="!showablbum" class="homepage_list1">
      <p class="title list1_title">歌曲推荐</p>
      <div @mouseleave="moveoutinbox2" @mouseenter="moveinbox2" class="box">
        <div
          ref="previous"
          @click="previous(1)"
          :style="{opacity: turnpage2}"
          class="svgs previous"
        >
          <SvgIcon class="icon" name="previous" />
        </div>
        <div ref="list1_box" class="scroll">
          <div
            class="homepage_header_content list1_content"
            v-for="(item,index) in music.random"
            :key="index"
          >
            <ul>
              <li>
                <img :src="item.img_url" />
                <div class="prettify"></div>
                <div @click="playermusic(item)" class="box_play">
                  <SvgIcon name="play" />
                </div>
              </li>
              <li class="describe">{{item.musicname}}</li>
            </ul>
          </div>
        </div>
        <div
          ref="nextdown"
          @click="nextdown(1)"
          :style="{opacity: turnpage2}"
          class="svgs nextdown"
        >
          <SvgIcon class="icon" name="nextdown" />
        </div>
      </div>
    </div>
    <div v-show="showablbum" class="album_content">
      <div class="album_particularss">
        <album_particulars :setalbum="setalbum" />
      </div>
      <div class="presentations">
        <presentation @getmusic="getmusic" :setdata="setdata" />
      </div>
    </div>
  </div>
</template>

<script>
import presentation from "./music_library/presentation";
import album_particulars from "./music_library/album/album_particulars";
import { Overlay, Loading } from "vant";
export default {
  props: {
    check_back: Boolean,
  },
  components: {
    album_particulars,
    presentation,
    [Overlay.name]: Overlay,
    [Loading.name]: Loading,
  },
  data() {
    return {
      showthepage:true,
      defaultImg:
        'this.src="' + require("../../assets/image/default.jpg") + '"',
      turnpage: 0,
      turnpage2: 0,
      svgopacity: 0,
      showablbum: false,
      setalbum: "",
      setdata: "",
      music: {
        random: [],
      },
      album: {
        recommend: [],
        selected: [],
      },
    };
  },
  methods: {
    playalbum(obj) {
      this.$emit("getalbum", obj);
    },
    getselectedalbum() {
      this.$fetchGet("/prmusic/user/getselectedalbum").then((res) => {
        this.album.selected = res.message;
        this.showthepage = false;
      });
    },
    goinalbum(obj) {
      this.showablbum = true;
      this.setalbum = obj;
      this.setdata = obj.album_id;
    },
    getalbumrecommend() {
      this.$fetchGet("/prmusic/user/getalbumrecommend").then((res) => {
        this.album.recommend = res.message;
        this.showthepage = false;
      });
    },
    getrancomMusic() {
      this.$fetchGet("/prmusic/user/getrandom").then((res) => {
        this.music.random = res.message;
        this.showthepage = false;
      });
    },
    playermusic(obj) {
      this.$emit("getmusic", obj);
    },
    getmusic(obj) {
      this.$emit("getmusic", obj);
    },
    previous(e) {
      let boxscor;
      if (e != 1) {
        boxscor = this.$refs.list_box;
      } else {
        boxscor = this.$refs.list1_box;
      }
      let flag = 0;
      let scrollmove = setInterval(() => {
        boxscor.scrollLeft = boxscor.scrollLeft - 10;
        flag++;
        if (flag == 30) {
          clearInterval(scrollmove);
        }
      }, 10);
    },
    nextdown(e) {
      let boxscor;
      if (e != 1) {
        boxscor = this.$refs.list_box;
      } else {
        boxscor = this.$refs.list1_box;
      }
      let flag = 0;
      let scrollmove = setInterval(() => {
        boxscor.scrollLeft = boxscor.scrollLeft + 10;
        flag++;
        if (flag == 30) {
          clearInterval(scrollmove);
        }
      }, 10);
    },
    moveinbox() {
      clearInterval(openturnpage);
      this.turnpage = 0;
      let openturnpage = setInterval(() => {
        this.turnpage += 0.1;
        if (this.turnpage >= 1) {
          clearInterval(openturnpage);
        }
      }, 20);
    },
    moveoutinbox() {
      clearInterval(openturnpage);
      this.turnpage = 1;
      let openturnpage = setInterval(() => {
        this.turnpage -= 0.1;
        if (this.turnpage <= 0) {
          clearInterval(openturnpage);
        }
      }, 20);
    },
    moveinbox2() {
      clearInterval(openturnpage);
      this.turnpage2 = 0;
      let openturnpage = setInterval(() => {
        this.turnpage2 += 0.1;
        if (this.turnpage2 >= 1) {
          clearInterval(openturnpage);
        }
      }, 20);
    },
    moveoutinbox2() {
      clearInterval(openturnpage);
      this.turnpage2 = 1;
      let openturnpage = setInterval(() => {
        this.turnpage2 -= 0.1;
        if (this.turnpage2 <= 0) {
          clearInterval(openturnpage);
        }
      }, 20);
    },
  },
  mounted() {
    this.getselectedalbum();
    this.getalbumrecommend();
    this.getrancomMusic();
  },
  watch: {
    check_back() {
      this.showablbum = false;
    },
  },
};
</script>

<style scoped>
.homepage {
  margin-top: 30px;
  width: 100%;
  height: calc(100% - 30px);
  text-align: center;
  -moz-user-select: none; /*火狐*/
  -webkit-user-select: none; /*webkit浏览器*/
  -ms-user-select: none; /*IE10*/
  -khtml-user-select: none; /*早期浏览器*/
  user-select: none;
}

.homepage_header {
  width: 100%;
}

.title {
  font-size: 30px;
  font-weight: bold;
  margin-right: 50%;
}

.box {
  width: 1200px;
  white-space: nowrap;
  overflow-x: scroll;
  margin: 0 auto;
  position: relative;
}

.box::-webkit-scrollbar {
  display: none;
}

.scroll {
  width: 100%;
  overflow-x: scroll;
  margin: 0 auto;
  position: relative;
}

.scroll::-webkit-scrollbar {
  display: none;
}

.homepage_header_content {
  display: inline-block;
  width: 300px;
  height: 250px;
  overflow: hidden;
  margin-top: 20px;
  position: relative;
}

.prettify {
  position: absolute;
  width: 240px;
  height: 40%;
  bottom: 0px;
  left: 30px;
  background-image: linear-gradient(to top, black, transparent);
  border-radius: 0 0 5px 5px;
  z-index: 0;
}

.homepage_header_content:last-child {
  margin-right: 50%;
}

.homepage_header_content ul li {
  position: relative;
}

.homepage_header_content img {
  width: 80%;
  height: 150px;
  border-radius: 5px;
  box-shadow: 1px 0px 10px 3px black;
  margin-top: 10px;
}

.box_play {
  position: absolute;
  font-size: 30px;
  bottom: 10px;
  right: 10%;
  border-radius: 50%;
  color: gray;
  opacity: 0.5;
  transition: all 0.3s ease;
  z-index: 2;
}

.box_play:hover {
  font-size: 35px;
  color: white;
  opacity: 1;
  transition: all 0.3s ease;
}

.list1_content:last-child {
  margin-right: 0px;
}

.describe {
  float: left;
  margin-left: 30px;
  font-weight: bold;
  margin-top: 15px;
}

.homepage_list1 {
  width: 100%;
}

.svgs {
  position: absolute;
  width: 40px;
  height: 40px;
  background-color: white;
  color: black;
  border-radius: 50%;
  top: 100px;
}

.svgs:hover {
  background-color: gainsboro;
}

.icon {
  margin-top: 12px;
}

.previous {
  z-index: 999;
}

.nextdown {
  right: 0;
}

.album_content {
  width: 80%;
  height: calc(100% - 30px);
  text-align: left;
  margin: auto;
  overflow: hidden;
}

.album_particularss {
  margin-left: 10px;
  min-height: 93px;
  background-color: rgba(10, 10, 10, 0.3);
  border-bottom: 1px solid gainsboro;
  border-top: 1px solid gainsboro;
}

.presentations {
  width: 100%;
  height: calc(100% - 280px);
  overflow: auto;
}

@media only screen and (max-width: 850px) {
  .homepage_header_content {
    width: 200px;
    height: 180px;
  }
  .homepage_header_content::after {
    width: 160px;
    height: 40%;
    bottom: 70px;
    left: 20px;
  }
  .homepage_header_content:last-child {
    margin-right: 0;
  }
  .homepage_header_content img {
    height: 100px;
  }
  .box,
  .homepage_list1 {
    width: 100%;
  }
  .svgs {
    top: 110px;
  }
  .previous,
  .nextdown {
    display: none;
  }
  .box_play {
    opacity: 0.7;
  }
  .album_content {
    width: 100%;
  }
  .presentations {
    height: calc(100% - 93px);
  }
  .prettify {
    width: 160px;
    bottom: 0px;
    left: 20px;
  }
}
</style>
