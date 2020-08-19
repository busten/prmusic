<template>
  <div class="search_result">
    <p class="search_result_title">搜索结果<span @click="backsearch" style="float:right;">返回</span></p>
    <div
      v-show="isdevelop != 2 & !isalbum"
      :class="{develop_srh:isdevelop == 1}"
      class="search_result_header"
    >
      <p class="search_result_box_title">
        搜到音乐
        <span @click="develop_srh" style="float:right;">
          <span v-if="isdevelop !=1">展开</span>
          <span v-if="isdevelop ==1">折叠</span>
        </span>
      </p>
      <div class="result_box">
        <component :is="'presentation'" />
      </div>
    </div>
    <div v-show="isdevelop != 1" :class="{develop_srh:isdevelop == 2 || isalbum}" class="search_result_centel">
      <p class="search_result_box_title">
        {{sc_album_name}}
        <span v-show="!isalbum" @click="develop_srb" style="float:right;">
          <span v-if="isdevelop !=2">展开</span>
          <span v-if="isdevelop ==2">折叠</span>
        </span>
        <span @click="back" v-show="isalbum" style="float:right;">返回</span>
      </p>
      <div class="result_box">
        <li v-show="!isalbum" @click="changeinalbum" v-for="index in 100" :key="index">
          <img src="../../../assets/image/default.jpg" />
          <div>
            <p style="margin-top:5px;">合辑名称</p>
            <p>0首歌曲</p>
          </div>
        </li>
        <component v-show="isalbum" :is="'presentation'" />
      </div>
    </div>
  </div>
</template>

<script>
import presentation from "../music_library/presentation";
import album from "../music_library/album";
export default {
  components: {
    presentation,
  },
  data() {
    return {
      sc_album_name: "搜到合辑",
      isdevelop: 0,
      isalbum: false,
    };
  },
  methods: {
    backsearch(){
      this.$emit('backsearch',true)
    },
    develop_srh() {
      if (this.isdevelop == 1) {
        this.isdevelop = 0;
      } else {
        this.isdevelop = 1;
      }
    },
    develop_srb() {
      if (this.isdevelop == 2) {
        this.isdevelop = 0;
      } else {
        this.isdevelop = 2;
      }
    },
    changeinalbum() {
      this.isalbum = !this.isalbum;
      this.sc_album_name = "合辑名称";
    },
    back(){
      this.isalbum = false;
      this.sc_album_name = "搜到合辑";
    }
  },
};
</script>

<style scoped>
.search_result {
  width: 80%;
  height: 100%;
}

.search_result_title {
  width: calc(100% - 10px);
  padding-left: 10px;
  padding-bottom: 5px;
  font-size: 18px;
  background-color: gray;
}

.search_result_title span{
  cursor: pointer;
}

.search_result_header {
  width: 100%;
  height: 40%;
}

.search_result_box_title {
  width: calc(100% - 10px);
  padding-left: 10px;
  padding-bottom: 5px;
}

.search_result_box_title span {
  cursor: pointer;
}

.result_box {
  width: 100%;
  height: 100%;
  overflow-x: hidden;
  overflow-y: auto;
}

.result_box li {
  width: 100%;
  height: 50px;
}

.result_box li:hover {
  background-color: rgba(184, 184, 184, 0.3);
}

.result_box li img {
  width: 70px;
  height: 40px;
  margin: 5px;
  vertical-align: middle;
  float: left;
}

.result_box li div {
  width: calc(100% - 80px);
  height: 40px;
  float: left;
  overflow: hidden;
}

.result_box::-webkit-scrollbar {
  display: none;
}

.search_result_centel {
  width: 100%;
  height: 40%;
  margin-top: 30px;
}

.develop_srh {
  margin-top: 5px;
  height: calc(100% - 85px);
}

@media only screen and (max-width: 850px) {
  .search_result {
    width: 100%;
  }
}
</style>
