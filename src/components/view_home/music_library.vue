<template>
  <div class="music_library">
    <div class="music_library_box">
      <div class="box_header">
        <ul>
          <li
            @click="clickli(index)"
            v-for="(item,index) in box_header_title"
            :key="index"
            :class="{underline:current == index}"
          >{{item}}</li>
          <li v-show="show_back" @click="back" class="back">Back</li>
        </ul>
      </div>
      <div class="box_centent">
        <transition name="fade" mode="out-in">
          <component :is="comname" :isback="isback" @isbacks="isbacks" />
        </transition>
      </div>
    </div>
  </div>
</template>

<script>
import new_music from "../../components/view_home/music_library/presentation";
import album from "../../components/view_home/music_library/album";
import fortype from "../../components/view_home/music_library/type";
import entire from "../../components/view_home/music_library/presentation";
export default {
  components: {
    new_music,
    album,
    fortype,
    entire,
  },
  data() {
    return {
      comname: "new_music",
      box_header_title: ["新曲上线", "合辑", "歌手", "全部"],
      current: 0,
      isback: false,
      show_back: false,
    };
  },
  methods: {
    clickli(index) {
      this.current = index; //获取点击对象
      switch (index) {
        case 0:
          this.comname = "new_music";
          break;
        case 1:
          this.comname = "album";
          break;
        case 2:
          this.comname = "fortype";
          break;
        case 3:
          this.comname = "entire";
          break;
      }
    },
    back() {
      this.isback = !this.isback;
      this.show_back = !this.show_back;
    },
    isbacks(e) {
      this.show_back = e;
    },
  },
};
</script>

<style>
.music_library {
  width: 100%;
  height: 100%;
}

.music_library_box {
  width: 80%;
  height: 100%;
  margin: 0 auto;
}

.box_header {
  width: 100%;
  height: 50px;
  position: relative;
}

.box_header::after {
  width: 100%;
  content: "";
  position: absolute;
  border-bottom: 1px solid gainsboro;
  bottom: 0;
}

.box_header ul li {
  font-size: 15px;
  display: inline-block;
  line-height: 30px;
  margin-right: 10px;
  cursor: pointer;
  outline: none;
}

.underline {
  border-bottom: 1px solid white;
  border-width: 2px;
}

.box_centent {
  width: 100%;
  height: calc(100% - 50px);
  overflow: auto;
}

.box_centent::-webkit-scrollbar {
  display: none;
}

.back {
  float: right;
}

@media only screen and (max-width: 850px) {
  .music_library_box {
    width: 100%;
  }

  .box_header ul{
    margin-left: 10px;
  }
}
</style>
