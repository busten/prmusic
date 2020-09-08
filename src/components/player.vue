<template>
  <div class="player">
    <div @click="change_bar_btn" class="progress_bar">
      <div :style="{width:progress}" class="progress_bar_box">
        <div class="progress_bar_btn"></div>
      </div>
    </div>
    <div class="music_message">
      <div>
        <img :src="music.img_url" />
      </div>
      <ul>
        <li>{{music.name}}</li>
        <li>{{music.song}}</li>
        <li>{{music_titme}}</li>
      </ul>
    </div>
    <ul class="play_btn">
      <li @click="last_music">
        <SvgIcon class="player_icon_last" name="lastsong" />
      </li>
      <li @click="playerd">
        <SvgIcon v-show="!iscushion" class="player_icon_play" :name="playicon" />
        <van-loading size="40" :vertical="true" v-show="iscushion" color="white" />
      </li>
      <li @click="next_music">
        <SvgIcon class="player_icon_next" name="nextsong" />
      </li>
      <li>
        <div @mouseleave="movevlomeicon = false" class="volume_box">
          <div @click="setvolume" @mouseenter="movevlomeicon = true" class="horn">
            <SvgIcon class="volume" :name="volumeicon" />
          </div>
          <div @click="setvolumn" @mouseenter="movevlomeicon = true" class="roundbar_box">
            <div :class="{move_volume:movevlomeicon}" class="roundbar">
              <div :style="{width:volume_width+'%'}" class="volume_btn_box">
                <div class="volume_btn"></div>
              </div>
            </div>
          </div>
        </div>
      </li>
      <li @click="open_music_list">
        <SvgIcon class="list_page" name="list" />
        <span class="music_number">{{music_list.length}}</span>
      </li>
    </ul>
    <p class="music_time">{{total_time}}</p>
    <audio
      ref="audio"
      @waiting="cushion"
      @playing="broadcast"
      @pause="suspend"
      preload="auto"
      :src="music.url"
    />
    <div :style="{height:music_list_height}" class="music_list">
      <p class="music_list_title">播放列表</p>
      <div class="music_list_display">
        <img :src="music.img_url" />
      </div>
      <div class="music_list_box">
        <ul>
          <li
            @click="playmusic(items,index)"
            v-for="(items,index) in music_list"
            :class="{isthisplay:music.name == items.musicname}"
            :key="index"
          >
            <span class="serial_number">{{index+1}}</span>
            <div>
              <img :src="items.img_url" />
            </div>
            <div>
              <p>{{items.musicname}}</p>
              <p>
                {{items.songname}}
                <span class="music_list_time">00:00</span>
              </p>
            </div>
          </li>
        </ul>
      </div>
    </div>
  </div>
</template>

<script>
import { Loading, Notify } from "vant";
export default {
  props: {
    setdata: "",
    setalbum: "",
  },
  components: {
    [Loading.name]: Loading,
    [Notify.name]: Notify,
  },
  data() {
    return {
      playicon: "play",
      play_status: false,
      total_time: "00:00",
      music_titme: "",
      progress: "0%",
      fullscreen: false,
      iscushion: false,
      inter: "",
      music_list_height: "0",
      isopen_music_list: false,
      music: {
        name: "暂无歌曲",
        song: "",
        url: "",
        img_url: require("../assets/image/default.jpg"),
      },
      music_list: [],
      nowmusic_listid: 0,
      movevlomeicon: false,
      volume_width: 100,
      isilence: false,
      recordvolume: 0,
      volumeicon: 'volume'
    };
  },
  methods: {
    setvolume() {
      this.isilence = !this.isilence;
      if (this.isilence) {
        this.recordvolume = this.volume_width;
        this.volume_width = 0;
        this.volumeicon = "novuloumn";
      } else {
        this.volume_width = this.recordvolume;
        this.volumeicon = "volume";
      }
    },
    setvolumn($event) {
      var num = ($event.offsetX + 6).toFixed(2);
      if (num <= 100) {
        this.volume_width = num;
      }
    },
    last_music() {
      this.setplaytrack(false);
    },
    next_music() {
      this.setplaytrack(true);
    },
    setplaytrack(next) {
      var total = this.music_list.length - 1;
      var nownum = this.nowmusic_listid;
      if (next) {
        nownum++;
        if (nownum > total) {
          nownum = 0;
        }
      } else {
        nownum--;
        if (nownum < 0) {
          nownum = total;
        }
      }
      this.nowmusic_listid = nownum;
      var datas = this.music_list[nownum];
      this.setmusic(datas);
    },
    playmusic(datas, index) {
      this.setmusic(datas);
      this.nowmusic_listid = index;
    },
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
      this.play_status = true;
      this.get_music_time(obj);
    },
    suspend() {
      clearInterval(this.inter);
      this.playicon = "play";
      if (this.play_status) {
        setTimeout(() => {
          this.next_music();
        }, 500);
      }
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
    getalbumMusic(id) {
      if (id != undefined) {
        this.$fetchPost("/prmusic/user/getalbummusic", {
          album_id: JSON.stringify(id),
        }).then((res) => {
          if (res.message.length != 0) {
            this.music_list = res.message;
          } else {
            Notify({ type: "primary", message: "合辑中没有音乐或网络错误" });
          }
        });
      }
    },
    setmusic(datas) {
      clearInterval(this.inter);
      this.music.url = datas.url;
      this.music.img_url = datas.img_url;
      this.music.name = datas.musicname;
      this.music.song = datas.songname;
      this.$refs.audio.load();
      this.$fetchPost("/prmusic/user/getMusicUrl", {
        url: this.music.url,
      }).then((res) => {
        this.music.url = res.message;
        setTimeout(() => {
          this.$refs.audio
            .play()
            .then(() => {
              setTimeout(() => {}, this.$refs.audio.duration * 1000);
            })
            .catch((e) => {
              this.$refs.audio.pause();
            });
        }, 500);
      });
    },
  },
  watch: {
    setdata(datas) {
      clearInterval(this.inter);
      this.music_list = [datas];
      this.music.url = datas.url;
      this.music.img_url = datas.img_url;
      this.music.name = datas.musicname;
      this.music.song = datas.songname;
    },
    setalbum(obj) {
      this.getalbumMusic(obj.album_id);
    },
    music_list() {
      if (this.music_list != "") {
        var datas = this.music_list[0];
        this.setmusic(datas);
      }
    },
    volume_width(num) {
      this.$refs.audio.volume = (num / 100).toFixed(2);
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
  width: 80px;
  height: 50px;
  margin-top: 10px;
  margin-left: 10px;
}

.music_message ul {
  height: calc(100% - 40px);
  display: block;
  line-height: 100%;
  text-align: left;
  float: left;
  margin-top: 10px;
}

.music_message ul li {
  margin-left: 40px;
  line-height: 17px;
}

.isthisplay {
  background-color: rgba(255, 255, 255, 0.3);
}

.play_btn {
  display: inline-block;
  width: 370px;
  height: 100%;
  margin-left: 260px;
}

.play_btn li {
  display: inline-block;
  margin: 0px 10px;
  margin-top: 10px;
  line-height: 70px;
  position: relative;
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

.volume_box {
  width: 120px;
  height: 20px;
  float: left;
  position: relative;
}

.horn {
  float: left;
  width: 20px;
  height: 20px;
  position: relative;
}

.volume {
  font-size: 20px;
  position: absolute;
  top: 0;
  left: 0;
}

.roundbar_box {
  width: 125px;
  height: 8px;
}

.roundbar {
  width: 0px;
  height: 8px;
  background-color: gray;
  position: absolute;
  left: 25px;
  top: 5px;
  border-radius: 5px;
  transition: all 0.3s ease;
  overflow: hidden;
}

.volume_btn_box {
  width: 0;
  height: 100%;
  min-width: 10px;
  background-color: gainsboro;
  border-radius: 5px;
  position: relative;
}

.move_volume {
  width: 95px;
  transition: all 0.3s ease;
}

.volume_btn {
  width: 10px;
  height: 10px;
  border-radius: 50%;
  background-color: white;
  border: 1px solid gray;
  position: absolute;
  top: -2px;
  right: 0;
}

.list_page {
  font-size: 20px;
}

.music_number {
  margin-left: 5px;
  cursor: default;
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
  cursor: default;
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
  width: 650px;
  height: 450px;
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

.music_list_box ul {
  width: 100%;
  height: calc(100% - 40px);
  overflow: auto;
}

.music_list_box ul::-webkit-scrollbar {
  display: none;
}

.music_list_box li {
  width: 100%;
  height: 50px;
  text-align: left;
}

.music_list_box li:hover {
  background-color: gray;
}

.serial_number {
  width: 30px;
  line-height: 40px;
  float: left;
  text-align: center;
  margin: 3px 10px;
}

.music_list_box li div {
  margin-top: 3px;
  float: left;
  white-space: nowrap;
  position: relative;
}

.music_list_box li div:last-child {
  width: calc(100% - 140px);
  margin-left: 10px;
  overflow: hidden;
}

.music_list_box img {
  width: 70px;
  height: 40px;
  border: 1px solid white;
}

.music_list_time {
  float: right;
}

@media only screen and (max-width: 850px) {
  .music_list_display {
    display: none;
  }
  .music_list_box {
    width: 100%;
  }
  .play_btn {
    width: 150px;
    margin-left: 90px;
  }
  .volume_box {
    display: none;
  }
}

@media only screen and (max-width: 320px) {
  .play_btn li {
    margin: 0px 5px;
    margin-top: 10px;
  }
  .player_icon_last {
    font-size: 15px;
  }

  .player_icon_play {
    font-size: 30px;
  }

  .player_icon_next {
    font-size: 16px;
  }
}
</style>
