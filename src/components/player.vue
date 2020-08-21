<template>
  <div class="player">
    <div @click="change_bar_btn" class="progress_bar">
      <div :style="{width:progress}" class="progress_bar_box">
        <div class="progress_bar_btn"></div>
      </div>
    </div>
    <div class="music_message">
      <div>
        <img src :onerror="defaultImg" />
      </div>
      <ul>
        <li>歌曲名称</li>
        <li>歌手</li>
        <li>{{music_titme}}</li>
      </ul>
    </div>
    <ul class="play_btn">
      <li>
        <SvgIcon class="player_icon_last" name="lastsong" />
      </li>
      <li @click="playerd">
        <SvgIcon v-show="!iscushion" class="player_icon_play" :name="playicon" />
        <van-loading size="40" :vertical="true" v-show="iscushion" color="white" />
      </li>
      <li>
        <SvgIcon class="player_icon_next" name="nextsong" />
      </li>
      <li @click="open_music_list">
        <SvgIcon class="list_page" name="list" />
        <span class="music_number">0</span>
      </li>
    </ul>
    <p class="music_time">{{total_time}}</p>
    <audio
      ref="audio"
      @waiting="cushion"
      @playing="broadcast"
      @pause="suspend"
      preload="auto"
      :src="url_music"
    />
    <div :style="{height:music_list_height}" class="music_list">
      <p class="music_list_title">播放列表</p>
      <div class="music_list_display">
        <img src="../assets/image/default.jpg" />
      </div>
      <div class="music_list_box">
        <ul>
          <li v-for="index in 100" :key="index">
            <span class="serial_number">{{index}}</span>
            <div>
              <img src="../assets/image/default.jpg" />
            </div>
            <div>
              <p>歌曲名称</p>
              <p>歌手<span class="music_list_time">00:00</span></p>
            </div>
          </li>
        </ul>
      </div>
    </div>
  </div>
</template>

<script>
import { Loading } from "vant";
export default {
  components: {
    [Loading.name]: Loading,
  },
  data() {
    return {
      defaultImg: 'this.src="' + require("../assets/image/default.jpg") + '"',
      url_music: '',
      playicon: "play",
      play_status: false,
      total_time: "00:00",
      music_titme: "00:00",
      progress: "0%",
      fullscreen: false,
      iscushion: false,
      inter: "",
      music_list_height: "0",
      isopen_music_list: false,
    };
  },
  methods: {
    open_music_list() {
      if (this.isopen_music_list) {
        this.music_list_height = "0";
        this.isopen_music_list = false;
      } else {
        this.music_list_height = "calc(100% - 70px)";
        this.isopen_music_list = true;
      }
    },
    change_bar_btn(el) {
      var getcheckX = el.offsetX;
      var getwindowswidth = window.innerWidth;
      var proportion = (getcheckX / getwindowswidth).toFixed(2);
      var total = parseInt(this.$refs.audio.duration);
      this.$refs.audio.currentTime = total * proportion;
    },
    full_screen_switch() {
      //全屏
      let element = document.documentElement;
      if (this.fullscreen) {
        document.exitFullscreen();
        this.fullscreen = false;
      } else {
        element.requestFullscreen();
        this.fullscreen = true;
      }
    },
    broadcast(obj) {
      this.iscushion = false;
      this.playicon = "pause";
      this.get_music_time(obj);
    },
    suspend() {
      clearInterval(this.inter);
      this.playicon = "play";
      this.play_status = false;
    },
    playerd(obj) {
      clearInterval(this.inter);
      if (this.play_status) {
        this.$refs.audio.pause();
        this.play_status = false;
      } else {
        this.$refs.audio.play();
        this.play_status = true;
      }
    },
    cushion() {
      this.iscushion = true;
    },
    get_music_time(obj) {
      var num = parseInt(this.$refs.audio.duration);
      var minute = (Array(2).join(0) + parseInt(num / 60)).slice(-2);
      var secend = (Array(2).join(0) + (num % 60)).slice(-2);
      this.music_titme = minute + ":" + secend;
      this.inter = setInterval(() => {
        var nowtime = parseInt(obj.target.currentTime);
        var num = parseInt(this.$refs.audio.duration - nowtime);
        var minute = (Array(2).join(0) + parseInt(num / 60)).slice(-2);
        var secend = (Array(2).join(0) + (num % 60)).slice(-2);
        var nowtime = parseInt(obj.target.currentTime);
        this.total_time = minute + ":" + secend;
        this.progress =
          ((obj.target.currentTime / this.$refs.audio.duration) * 100).toFixed(
            2
          ) + "%";
        if (minute == 0 && secend == 0) {
          this.progress = "0%";
          this.music_titme = "00:00";
          clearInterval(this.inter);
        }
      }, 100);
    },
  },
};
</script>

<style scoped>
.player {
  width: 100%;
  height: 100%;
  position: fixed;
  background-color: rgba(10, 10, 10, 0.5);
  text-align: center;
}

.progress_bar {
  width: 100%;
  height: 3px;
  position: absolute;
  top: 0;
  cursor: pointer;
  background-color: gray;
  z-index: 99;
}

.progress_bar_box {
  width: 0%;
  height: 3px;
  background-color: white;
  position: relative;
}

.progress_bar_btn {
  position: absolute;
  width: 10px;
  height: 10px;
  border-radius: 50%;
  background-color: white;
  top: -3px;
  right: 0;
}

.progress_bar_btn:hover {
  background-color: white;
}

.music_message {
  display: block;
  width: 20%;
  height: 100%;
  position: absolute;
  color: white;
}

.music_message div {
  display: block;
  width: 60px;
  height: 100%;
  float: left;
}

.music_message img {
  width: 50px;
  height: 50px;
  margin-top: 10px;
  margin-left: 10px;
}

.music_message ul {
  width: 100px;
  height: calc(100% - 10px);
  display: block;
  line-height: 100%;
  text-align: left;
  float: left;
  margin-top: 10px;
}

.music_message ul li {
  margin-left: 10px;
  line-height: 17px;
}

.play_btn {
  display: inline-block;
  width: 200px;
  height: 100%;
  margin-left: 110px;
}

.play_btn li {
  display: inline-block;
  margin: 0px 10px;
  margin-top: 10px;
  line-height: 70px;
}

.player_icon_last {
  font-size: 20px;
}

.player_icon_play {
  font-size: 40px;
}

.player_icon_next {
  font-size: 21px;
}

.list_page {
  font-size: 20px;
}

.music_number {
  margin-left: 5px;
}

.play_btn li {
  color: gray;
}

.play_btn li:hover {
  color: white;
}

.music_time {
  display: block;
  color: white;
  float: right;
  line-height: 70px;
  margin-right: 20px;
}

.music_list {
  width: 100%;
  background-color: black;
  position: fixed;
  transition: all 0.5s ease;
  bottom: 70px;
  overflow: hidden;
}

.music_list_title {
  width: 100%;
  color: white;
  background-color: rgba(255, 255, 255, 0.2);
  text-align: center;
  padding: 10px 0;
}

.music_list_display {
  width: 50%;
  height: 100%;
  display: flex;
  justify-content: center;
  align-items: center;
  float: left;
}

.music_list_display img {
  width: 400px;
  height: 300px;
  margin-bottom: 50px;
  vertical-align: top;
}

.music_list_box {
  display: block;
  width: 50%;
  height: 100%;
  float: left;
  color: white;
}

.music_list_box ul{
  width: 100%;
  height: calc(100% - 40px);
  overflow: auto;
}

.music_list_box ul::-webkit-scrollbar{
  display: none;
}

.music_list_box li {
  width: 100%;
  height: 50px;
  text-align: left;
}

.music_list_box li:hover{
  background-color: gray;
}

.serial_number{
  width: 30px;
  line-height: 40px;
  float: left;
  text-align: center;
  margin: 3px 10px;
}

.music_list_box li div{
  margin-top: 3px;
  float: left;
  white-space: nowrap;
  position: relative;
}

.music_list_box li div:last-child{
  width: calc(100% - 140px);
  margin-left: 10px;
  overflow: hidden;
}

.music_list_box img{
  width: 70px;
  height: 40px;
  border: 1px solid white;
}

.music_list_time{
  float: right;
}

@media only screen and (max-width: 850px) {
  .music_list_display {
    display: none;
  }
  .music_list_box{
    width: 100%;
  }
  .play_btn{
    margin-left: 60px;
  }
}
</style>
