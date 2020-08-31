<template>
  <div class="admin_music">
    <van-popup v-model="showPopup">
      <div class="popup_box">
        <p class="up_box">
          <span>选择音乐文件:</span>
          <van-uploader accept=".mp3, .mp4, .flac">
            <van-button icon="plus" type="info">上传文件</van-button>
          </van-uploader>
        </p>
        <p class="up_box">
          <span>歌曲名称:</span>
          <input type="text" placeholder="未选择文件" />
        </p>
        <p class="up_box">
          <span>选择歌手:</span>
          <select>
            <option>暂无</option>
          </select>
        </p>
        <p class="up_box">
          <span>选择合辑:</span>
          <select>
            <option>暂无</option>
          </select>
        </p>
        <p class="adddata">
          <van-button @click="addmusics" type="info">添加</van-button>
        </p>
      </div>
    </van-popup>
    <div class="admin_music_header">
      <p>音乐歌曲管理页</p>
    </div>
    <div class="admin_music_centel">
      <div class="admin_music_centel_header">
        <li>
          <input type="checkbox" />
        </li>
        <li class="music_name">歌曲名称</li>
        <li class="create_time">收录时间</li>
        <li class="forsong">歌手</li>
        <li class="foralbum">所属合辑</li>
        <li class="btn_box">
          <van-button type="primary" @click="addmusic">添加</van-button>
          <van-button type="danger" @click="deleteallmusic">删除所选</van-button>
        </li>
      </div>
      <div class="admin_music_centel_box">
        <div class="admin_music_centel_data" v-for="index in 100" :key="index">
          <li>
            <input type="checkbox" />
          </li>
          <li class="music_name">歌曲名称</li>
          <li class="create_time">收录时间</li>
          <li class="forsong">歌手</li>
          <li class="foralbum">所属合辑</li>
          <li class="btn_box">
            <van-button type="danger" @click="deletemusic">删除</van-button>
          </li>
        </div>
      </div>
      <div class="admin_music_floder">
        <van-pagination
          v-model="currentPage"
          :total-items="200"
          :show-page-size="10"
          force-ellipses
        />
      </div>
    </div>
  </div>
</template>

<script>
import { Button, Dialog, Popup, Uploader, Pagination } from "vant";
export default {
  components: {
    [Button.name]: Button,
    [Dialog.name]: Dialog,
    [Popup.name]: Popup,
    [Uploader.name]: Uploader,
    [Pagination.name]: Pagination,
  },
  data() {
    return {
      datas: 100,
      showPopup: false,
      currentPage: 20,
    };
  },
  methods: {
    addmusics() {
      this.showPopup = !this.showPopup;
    },
    addmusic() {
      this.showPopup = !this.showPopup;
    },
    deleteallmusic() {
      Dialog.confirm({
        title: "删除所选?",
        message: "是否删除选中的所有歌曲?",
      })
        .then(() => {
          // on confirm
        })
        .catch(() => {
          // on cancel
        });
    },
    deletemusic() {
      Dialog.confirm({
        title: "删除?",
        message: "是否删除歌曲?",
      })
        .then(() => {
          // on confirm
        })
        .catch(() => {
          // on cancel
        });
    },
  },
};
</script>

<style scoped>
select {
  border: none;
  outline: none;
  margin-left: 30px;
  width: 100px;
}

option {
  border: none;
  outline: none;
  padding: 5px 10px;
  border-radius: 5px;
}
.van-button {
  height: auto;
  padding: 5px 10px;
  margin-left: 5px;
}

.van-pagination li {
}
.admin_music {
  width: 100%;
  height: 100%;
}

.admin_music_header {
  width: 100%;
  height: 50px;
  text-align: center;
  background-color: rgb(3, 33, 114);
  box-shadow: 0px 1px 20px 1px rgb(45, 80, 177);
}

.admin_music_header p {
  font-size: 20px;
  font-weight: bold;
  padding-top: 10px;
}

.admin_music_centel {
  width: 80%;
  max-width: 990px;
  height: calc(100% - 80px);
  margin: auto;
  margin-top: 20px;
  background-color: rgb(3, 33, 114);
  box-shadow: 0px 1px 20px 1px rgb(45, 80, 177);
  overflow: auto;
}

.admin_music_centel_header {
  width: 100%;
  min-width: 992px;
  height: 40px;
  border-bottom: 1px solid rgb(45, 80, 177);
}

.admin_music_centel_header li {
  float: left;
  padding: 10px;
  text-align: center;
}

.music_name {
  width: 400px;
}

.create_time {
  width: 100px;
}

.forsong {
  width: 100px;
}

.foralbum {
  width: 100px;
}

.btn_box {
  width: 150px;
}

.popup_box {
  width: 500px;
  height: 300px;
  background-color: rgb(5, 26, 83);
  text-align: center;
}

.popup_box input {
  margin: 30px;
  color: black;
}

.up_box {
  width: 100%;
  text-align: left;
  padding-top: 10px;
}

.admin_music_centel_box {
  width: 100%;
  height: calc(100% - 100px);
  overflow: auto;
  min-width: 992px;
  border-bottom: 1px solid rgb(45, 80, 177);
}

.adddata {
  position: absolute;
  right: 10px;
  bottom: 5px;
}

.admin_music_centel_data li {
  float: left;
  padding: 10px;
  text-align: center;
}

.admin_music_floder {
  width: 100%;
  height: 50px;
  min-width: 992px;
}

@media only screen and (max-width: 850px) {
  .admin_music_centel {
    width: 100%;
  }
}
</style>
