<template>
  <div class="admin_song">
    <van-popup v-model="showPopup">
      <div class="popup_box">
        <p>歌手名称</p>
        <input type="text">
        <p class="adddata"><van-button @click="addsongs" type="info">添加</van-button></p>
      </div>
    </van-popup>
    <div class="admin_song_header">
      <p>歌手管理页</p>
    </div>
    <div class="admin_song_centel">
      <div class="admin_song_centel_title">
        <li>
          <input type="checkbox" name id />
        </li>
        <li class="number">序列号</li>
        <li class="song_name_box">歌手</li>
        <li class="havemusic_box">含有歌曲</li>
        <li class="addtime_box">添加时间</li>
        <li class="btn_box">
          <van-button type="primary" @click="addsong">添加</van-button>
          <van-button type="danger" @click="deleteallsong">删除所选</van-button>
        </li>
      </div>
      <div class="admin_song_centel_maxbox">
        <div class="admin_song_centel_box" v-for="index in datas" :key="index">
          <li>
            <input type="checkbox" name id />
          </li>
          <li class="number">{{index}}</li>
          <li class="song_name_box">坂本真绫</li>
          <li class="havemusic_box">17</li>
          <li class="addtime_box">2020-08-26</li>
          <li class="btn_box">
            <van-button type="danger" @click="deletesong">删除</van-button>
          </li>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import { Button, Dialog, Popup, Uploader } from "vant";
export default {
  components: {
    [Button.name]: Button,
    [Dialog.name]: Dialog,
    [Popup.name]: Popup,
    [Uploader.name]: Uploader,
  },
  data() {
    return {
      datas: 100,
      showPopup:false,
    };
  },
  methods:{
    addsong(){
      this.showPopup = !this.showPopup;
    },
    addsongs(){
      this.showPopup = !this.showPopup;
    },
    deleteallsong(){
      Dialog.confirm({
        title: "删除所选?",
        message: "是否删除选中的所有歌手?",
      })
        .then(() => {
          // on confirm
        })
        .catch(() => {
          // on cancel
        });
    },
    deletesong(){
       Dialog.confirm({
        title: "删除?",
        message: "是否删除该歌手?",
      })
        .then(() => {
          // on confirm
        })
        .catch(() => {
          // on cancel
        });
    }
  }
};
</script>

<style scoped>
.van-button {
  height: auto;
  padding: 5px 10px;
  margin-left: 5px;
}
.admin_song {
  width: 100%;
  height: 100%;
}

.admin_song_header {
  width: 100%;
  height: 50px;
  text-align: center;
  background-color: rgb(3, 33, 114);
  box-shadow: 0px 1px 20px 1px rgb(45, 80, 177);
}

.admin_song_header p {
  font-size: 20px;
  font-weight: bold;
  padding-top: 10px;
}

.admin_song_centel {
  width: 760px;
  height: calc(100% - 80px);
  margin: auto;
  margin-top: 20px;
  background-color: rgb(5, 26, 83);
  box-shadow: 0px 1px 20px 1px rgb(45, 80, 177);
}

.admin_song_centel_maxbox{
  width: 100%;
  height: calc(100% - 50px);
  overflow: auto;
}

.admin_song_centel_title,
.admin_song_centel_box {
  width: 100%;
  height: 50px;
  border-bottom: 1px solid rgb(45, 80, 177);
}

.admin_song_centel_title li,
.admin_song_centel_box li {
  line-height: 50px;
  float: left;
  padding: 0px 10px;
  text-align: center;
}

.number {
  width: 70px;
}

.song_name_box {
  width: 200px;
}

.havemusic_box {
  width: 100px;
}

.addtime_box {
  width: 100px;
}

.btn_box {
  width: 150px;
}

.popup_box{
  width: 300px;
  height: 200px;
  background-color: rgb(5, 26, 83);
  text-align: center;
}

.popup_box input{
  margin: 30px;
  color: black;
}

.adddata{
  position: absolute;
  right: 10px;
  bottom: 5px;
}


@media only screen and (max-width: 850px) {
  .admin_song_centel {
    width: 100%;
  }
}
</style>
