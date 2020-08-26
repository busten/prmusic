<template>
  <div class="admin_album">
    <van-popup v-model="showPopup">
      <div class="popup_box">
        <p>合辑名称</p>
        <textarea></textarea>
        <div class="vupdate_btn">
          <van-button @click="update_window" type="info">修改</van-button>
        </div>
      </div>
    </van-popup>
    <van-popup v-model="showPopup1">
      <div class="popup_box">
        <table>
          <tr>
            <td>合辑名称:</td>
            <td>
              <input type="text" />
            </td>
          </tr>
          <tr>
            <td>封面图片:</td>
            <td>
              <van-uploader accept=".jpg" :after-read="afterRead" v-model="fileList" :max-count="1" multiple />
            </td>
          </tr>
          <tr>
            <td>简介:</td>
            <td>
              <textarea></textarea>
            </td>
          </tr>
        </table>
        <div class="vupdate_btn">
          <van-button @click="addalbum_window" type="info">添加</van-button>
        </div>
      </div>
    </van-popup>
    <div class="admin_album_box_header">
      <div class="mini_box">
        <div class="ablum_statistic">
          <p>合辑总数</p>
          <p>20</p>
        </div>
      </div>
    </div>
    <div class="admin_album_box">
      <div class="from_data">
        <div class="from_data_box">
          <div class="from_data_title">
            <li>
              <input type="checkbox" @change="checked" />
            </li>
            <li class="serial_number">序列号</li>
            <li class="album_name">合辑名称</li>
            <li class="cover_img">封面</li>
            <li class="create_time">创建时间</li>
            <li class="have_music">含有歌曲</li>
            <li class="synopsis">相关简介</li>
            <li class="operation">
              <van-button type="primary" @click="addalbum">添加</van-button>
              <van-button @click="deletealbumAll" type="danger">删除所选</van-button>
            </li>
          </div>
          <div class="from_data_cententl_box">
            <div class="from_data_cententl" v-for="index in datas" :key="index">
              <li>
                <p>
                  <input type="checkbox" v-model="checkedNames[index]" />
                </p>
              </li>
              <li class="serial_number">
                <p>{{index}}</p>
              </li>
              <li class="album_name">
                <p>一拳超人合辑</p>
              </li>
              <li class="cover_img">
                <img src="../../../assets/image/default.jpg" />
              </li>
              <li class="create_time">
                <p>2020-08-25</p>
              </li>
              <li class="have_music">
                <p>100</p>
              </li>
              <li class="synopsis">
                <span>《一拳超人》是ONE原作，村田雄介作画，连载于网络漫画杂志《邻座的Young jump》上的漫画。 [1-2] 原为ONE在个人网站上连载的练笔之作， [3] 后被喜爱该作的另一漫画家村田雄介在征得ONE同意后重新绘制而成。</span>
              </li>
              <li class="operation">
                <p>
                  <van-button @click="update" type="info">修改</van-button>
                  <van-button @click="deletealbum" type="danger">删除</van-button>
                </p>
              </li>
            </div>
          </div>
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
      showPopup: false,
      showPopup1: false,
      checkedNames: [],
      checkedAll: false,
      fileList: [],
    };
  },
  methods: {
    deletealbumAll() {
      Dialog.confirm({
        title: "删除所选?",
        message: "是否删除选中的所有合辑?",
      })
        .then(() => {
          // on confirm
        })
        .catch(() => {
          // on cancel
        });
    },
    update() {
      this.showPopup = !this.showPopup;
    },
    deletealbum() {
      Dialog.confirm({
        title: "删除?",
        message: "是否删除一拳超人合辑?",
      })
        .then(() => {
          // on confirm
        })
        .catch(() => {
          // on cancel
        });
    },
    update_window() {
      this.showPopup = !this.showPopup;
    },
    checked() {
      this.checkedAll = !this.checkedAll;
      this.checkedNames = [];
      if (this.checkedAll) {
        for (let index = 1; index <= this.datas; index++) {
          this.checkedNames.push(index);
          this.checkedNames[index] = true;
        }
      } else {
        for (let index = 1; index <= this.datas; index++) {
          this.checkedNames.push(index);
          this.checkedNames[index] = false;
        }
      }
    },
    addalbum() {
      this.showPopup1 = !this.showPopup1;
    },
    addalbum_window() {
      this.showPopup1 = !this.showPopup1;
    },
    afterRead(file) {
      
    },
  },
};
</script>

<style scoped>
textarea {
  resize: none;
  border: none;
  width: 80%;
  height: calc(100% - 150px);
  box-shadow: inset 0 0 5px 1px rgb(45, 80, 177);
  margin: auto;
  margin-left: 10%;
  background-color: transparent;
}

textarea:last-child {
  resize: none;
  border: none;
  width: 150%;
  height: 300px;
  box-shadow: inset 0 0 5px 1px rgb(45, 80, 177);
  margin: auto;
  background-color: transparent;
}

.van-popup {
  width: 100%;
  max-width: 500px;
}
.van-button {
  height: auto;
  padding: 5px 10px;
  margin-left: 5px;
}
.admin_album {
  width: 100%;
  height: 100%;
}

.admin_album_box_header {
  width: 100%;
  background-color: rgb(3, 33, 114);
  box-shadow: 0px 1px 20px 1px rgb(45, 80, 177);
}

.mini_box {
  width: 80%;
  height: 60px;
  padding: 10px;
  margin: auto;
}

.ablum_statistic {
  width: 100px;
  height: 100%;
  float: left;
}

.ablum_statistic p {
  text-align: center;
  font-size: 20px;
  font-weight: bold;
  margin-top: 5px;
}

.admin_album_box {
  width: 80%;
  height: calc(100% - 150px);
  margin: auto;
  margin-top: 40px;
  margin-bottom: 20px;
}

.from_data {
  width: 100%;
  height: 100%;
  background-color: rgb(3, 33, 114);
  box-shadow: 0px 1px 20px 1px rgb(45, 80, 177);
  overflow: auto;
}

.from_data_box {
  width: 100%;
  height: 100%;
  min-width: 1530px;
  overflow-x: auto;
  overflow-y: hidden;
  scrollbar-width: none;
}

.from_data_title {
  width: calc(100% - 20px);
  height: 30px;
  padding: 10px;
  border-bottom: 1px solid rgb(45, 80, 177);
  white-space: nowrap;
}

.from_data li {
  float: left;
  text-align: center;
  padding: 5px 10px;
}

.serial_number {
  width: 100px;
  height: 100%;
}

.album_name {
  width: 200px;
  height: 100%;
}

.cover_img {
  width: 150px;
  height: 100%;
}

.cover_img img {
  width: 100px;
  height: 75px;
}

.create_time {
  width: 150px;
  height: 100%;
}

.have_music {
  width: 80px;
  height: 100%;
}

.synopsis {
  width: 400px;
  height: 100%;
  white-space: normal;
  overflow: hidden;
}

.operation {
  width: 150px;
  height: 100%;
  float: right;
}

.from_data_cententl_box {
  width: 100%;
  height: calc(100% - 50px);
  overflow: auto;
  scrollbar-width: none;
}

.from_data_cententl {
  width: calc(100% - 20px);
  height: 90px;
  padding: 10px;
  border-bottom: 1px solid rgb(45, 80, 177);
}

.from_data_cententl p {
  width: 100%;
  height: 100%;
  line-height: 90px;
}

.popup_box {
  width: 100%;
  height: 500px;
  max-width: 500px;
  background-color: rgb(3, 33, 114);
  color: white;
}

.popup_box p {
  text-align: center;
  line-height: 30px;
}

.popup_box input {
  color: black;
}

.vupdate_btn {
  position: absolute;
  right: 10px;
  bottom: 5px;
}
</style>
