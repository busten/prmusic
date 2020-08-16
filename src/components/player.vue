<template>
  <div class="player">
    <div :style="{width:progress}" class="progress_bar">
      <div @click="change_bar_btn" class="progress_bar_btn"></div>
    </div>
    <div class="music_message"></div>
    <ul class="play_btn">
      <li>
        <SvgIcon class="player_icon_last" name="lastsong" />
      </li>
      <li @click="playerd">
        <SvgIcon v-show="!iscushion" class="player_icon_play" :name="playicon" />
        <van-loading size="40" :vertical="true" v-show="iscushion" color="white"/>
      </li>
      <li>
        <SvgIcon class="player_icon_next" name="nextsong" />
      </li>
    </ul>
    <p class="music_time">{{total_time}}</p>
    <audio ref="audio" @waiting="cushion" @playing="broadcast" @pause="suspend" preload="auto" :src="url_music" />
  </div>
</template>

<script>
import { Loading  } from "vant";
export default {
  components: {
    [Loading .name]: Loading ,
  },
  data() {
    return {
      url_music: 
      'https://data.prmusic.xyz/prmusic/audio/THE%20HERO%20%21%21%20%EF%BD%9E%E6%80%92%E3%82%8C%E3%82%8B%E6%8B%B3%E3%81%AB%E7%81%AB%E3%82%92%E3%81%A4%E3%81%91%E3%82%8D%EF%BD%9E.mp3?X-Amz-Algorithm=AWS4-HMAC-SHA256&X-Amz-Credential=mynameisvan%2F20200816%2F%2Fs3%2Faws4_request&X-Amz-Date=20200816T103646Z&X-Amz-Expires=432000&X-Amz-SignedHeaders=host&X-Amz-Signature=b845d2c75a9901de7301e978a7f9c36b396991acd1b44146cf1e895a3e2c364c',
      playicon: "play",
      play_status: false,
      total_time: "00:00",
      progress: "0%",
      fullscreen: false,
      iscushion: false,
      inter: "",
    };
  },
  methods: {
    change_bar_btn(){
      console.log("触发")
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
    cushion(){
      this.iscushion = true;
    },
    get_music_time(obj) {
      var num = parseInt(this.$refs.audio.duration);
      var minute = (Array(2).join(0) + parseInt(num / 60)).slice(-2);
      var secend = (Array(2).join(0) + (num % 60)).slice(-2);
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
  width: 0%;
  height: 3px;
  position: absolute;
  top: 0;
}

.progress_bar_btn {
  position: absolute;
  width: 10px;
  height: 10px;
  border-radius: 50%;
  background-color: gray;
  top: -3px;
  right: 0;
  cursor: pointer;
}

.progress_bar_btn:hover{
  background-color: white;
}

.progress_bar::before {
  position: absolute;
  content: "";
  width: 100%;
  height: 100%;
  background-color: gray;
  left: 0;
}

.music_message {
  display: block;
  width: 20%;
  height: 100%;
  position: absolute;
  color: white;
}

.play_btn {
  display: inline-block;
  width: 150px;
  height: 100%;
  margin-left: 60px;
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

.player_icon_last,
.player_icon_play,
.player_icon_next {
  color: gray;
}

.player_icon_last:hover,
.player_icon_play:hover,
.player_icon_next:hover {
  color: white;
}

.music_time {
  display: inline-block;
  color: white;
  float: right;
  line-height: 70px;
  margin-right: 20px;
}
</style>
