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
        </ul>
      </div>
      <div class="box_centent">
        <transition name="fade" mode="out-in">
          <component :is="comname" />
        </transition>
      </div>
    </div>
  </div>
</template>

<script>
import new_music from "../../components/view_home/music_library/new_music";
import album from '../../components/view_home/music_library/album';
export default {
  components: {
    new_music,album,
  },
  data() {
    return {
      comname:'new_music',
      box_header_title: ["新曲上线", "合辑", "类型", "全部"],
      current: 0,
    };
  },
  methods: {
    clickli(index) {
      this.current = index; //获取点击对象
      switch(index){
          case 0:this.comname = 'new_music';break;
          case 1:this.comname = 'album';break;
      }
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

.box_centent::-webkit-scrollbar{
    display: none;
}
</style>
