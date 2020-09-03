<template>
  <div class="discover">
    <ul class="lable_title">
      <li class="lable_title_li" @click="change_player_list">播放列表</li>
      <li class="lable_title_li" @click="change_album_list">专辑</li>
      <li v-show="isback" @click="back" style="float:right;margin-right:10px;">Back</li>
    </ul>
    <transition name="fade_com" mode="out-in">
      <component :backpage="isbackpage" @inplaylist="inplaylist" :is="comName" />
    </transition>
  </div>
</template>
<script>
import player_list from "../view_home/discover/player_list";
import album_list from "../view_home/discover/album_list";
export default {
  props:{
    check_back:Boolean
  },
  components: {
    player_list,
    album_list,
  },
  data() {
    return {
      comName: "player_list",
      isback: false,
      isbackpage: false,
    };
  },
  methods: {
    change_player_list() {
      this.comName = "player_list";
    },
    change_album_list() {
      this.comName = "album_list";
    },
    inplaylist(e) {
      this.isback = e;
    },
    back() {
      this.isback = false;
      this.isbackpage = !this.isbackpage;
    },
  },
  watch:{
    check_back(){
      this.comName = "player_list";
      this.isback = false;
      this.isbackpage = !this.isbackpage;
    }
  }
};
</script>

<style scoped>
.discover {
  width: 100%;
  height: 100%;
}

.lable_title {
  width: 80%;
  height: 30px;
  cursor: default;
  margin: auto;
}

.lable_title_li {
  width: 100px;
  height: 30px;
  display: inline-block;
  font-size: 15px;
  font-weight: bold;
  color: gainsboro;
  text-align: center;
  padding: 2px;
  transition-property: color;
  transition-duration: 0.3s;
  transition-timing-function: ease;
}

.lable_title_li:hover {
  color: white;
  font-size: 17px;
  padding: 0px 2px;
  transition-property: font-size, color;
  transition-duration: 0.3s;
  transition-timing-function: ease;
}

.fade_com-enter-active {
  transition: all 0.3s ease;
}
.fade_com-leave-active {
  transition: all 0.3s cubic-bezier(1, 0.5, 0.8, 1);
}
.fade_com-enter,
.fade_com-leave-to {
  transform: translateX(-100px);
  opacity: 0;
}

@media only screen and (max-width: 850px) {
  .lable_title {
    width: 100%;
  }
}
</style>
