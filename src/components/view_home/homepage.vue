<template>
  <div class="homepage">
    <div v-show="!showablbum" class="homepage_header">
      <p class="title">精选合辑</p>
      <div class="box">
        <div class="homepage_header_content" v-for="iten in 3" :key="iten">
          <ul>
            <li>
              <img src :onerror="defaultImg" />
              <SvgIcon class="box_play" name="play" />
            </li>
            <li class="describe">您的合辑</li>
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
          <div class="homepage_header_content list1_content" v-for="(item,index) in album.recommend" :key="index">
            <ul>
              <li>
                <img  @click="goinalbum(item)" :src="item.img_url" :onerror="defaultImg" />
                <div @click="playalbum(item)">
                <SvgIcon class="box_play" name="play" />
                </div>
              </li>
              <li  @click="goinalbum(item)" class="describe">{{item.albumname}}</li>
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
        <album_particulars :setalbum="setalbum"/>
      </div>
      <div class="presentations">
        <presentation @getmusic="getmusic" :setdata="setdata"/>
      </div>
    </div>
  </div>
</template>

<script>
import presentation from './music_library/presentation';
import album_particulars from './music_library/album/album_particulars';
export default {
  props:{
    check_back:Boolean
  },
  components:{
    album_particulars,presentation
  },
  data() {
    return {
      defaultImg:
        'this.src="' + require("../../assets/image/default.jpg") + '"',
      turnpage: 0,
      turnpage2: 0,
      svgopacity: 0,
      showablbum:false,
      setalbum: '',
      setdata: '',
      music: {
        random: [],
      },
      album:{
        recommend:[]
      }
    };
  },
  methods: {
    playalbum(obj){
       this.$emit("getalbum",obj)
    },
    goinalbum(obj){
      this.showablbum = true;
      this.setalbum = obj;
      this.setdata = obj.album_id;
    },
    getalbumrecommend(){
      this.$fetchGet("/prmusic/user/getalbumrecommend").then((res) => {
        this.album.recommend = res.message;
      });
    },
    getrancomMusic() {
      this.$fetchGet("/prmusic/user/getrandom").then((res) => {
        this.music.random = res.message;
      });
    },
    playermusic(obj) {
      this.$fetchPost("/prmusic/user/getMusicUrl", {
        url: obj.url,
      }).then((res) => {
        obj.url = res.message;
        this.$emit("getmusic",obj);
      });
    },
    getmusic(obj){
      this.$emit("getmusic",obj);
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
    this.getalbumrecommend();
    this.getrancomMusic();
  },
  watch:{
    check_back(){
      this.showablbum = false;
    }
  }
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
  transition: all 0.3s ease;
  margin-top: 10px;
}

.homepage_header_content img:hover {
  width: 85%;
  height: 170px;
  transition: all 0.3s ease;
}

.box_play {
  position: absolute;
  font-size: 30px;
  bottom: 10px;
  right: 10%;
  border-radius: 50%;
  color: gray;
  opacity: 0.3;
  transition: all 0.3s ease;
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

.album_content{
  width: 80%;
  height: calc(100% - 30px);
  text-align: left;
  margin: auto;
  overflow: hidden;
}

.album_particularss{
  margin-left: 10px;
  height: 250px;
}

.presentations{
  width: 100%;
  height: calc(100% - 250px);
  overflow: auto;
}

@media only screen and (max-width: 850px) {
  .homepage_header_content {
    width: 200px;
    height: 180px;
  }
  .homepage_header_content:last-child {
    margin-right: 0;
  }
  .homepage_header_content img{
    height: 100px;
  }
  .homepage_header_content img:hover {
  width: 85%;
  height: 110px;
  transition: all 0.3s ease;
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
}
</style>
