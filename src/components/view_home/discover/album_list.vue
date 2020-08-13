<template>
  <div class="album_list">
    <ul v-show="close_box" ref="getboxwidth" class="album_list_box">
      <li @click="change_play_list" ref="getliwidth" class="album_box" v-for="index in data_album_list" :key="index"></li>
      <li class="last_line" v-for="item in complement" :key="item+'a'"></li>
      <li style="text-align: center;" v-show="data_album_list == 0">您还没有收藏合辑</li>
    </ul>
    <component v-show="!close_box" :is="comName"/>
  </div>
</template>

<script>
import presentation from "../music_library/presentation";
export default {
  props:{
    backpage:{type: Boolean}
  },
  components:{
    presentation,
  },
  data() {
    return {
      data_album_list: 11,
      complement: 0,
      close_box: true,
      comName: '',
    };
  },
  methods:{
    change_play_list(){
      this.close_box = false;
      this.comName = 'presentation';
      this.$emit('inplaylist',true);
    },
    set_complement() {
      let getboxwidth = this.$refs.getboxwidth.clientWidth;
      let getliwidth = this.$refs.getliwidth[0].clientWidth + 10;
      let columns = parseInt(getboxwidth / getliwidth);
      let nums = this.data_album_list / columns;
      let rows =
        this.data_album_list % columns == 0 ? nums : parseInt(this.data_album_list / columns) + 1;
      this.complement = columns * rows - this.data_album_list;
    },
  },
  watch:{
    backpage:function(){
      this.close_box = true;
      this.comName = '';
    }
  },
  mounted(){
    this.set_complement();
    window.addEventListener("resize",this.set_complement);
  },
  destroyed(){
    this.$emit('inplaylist',false);
    window.removeEventListener("resize",this.set_complement);
  }
};
</script>

<style scoped>
.album_list {
  width: 80%;
  height: calc(100% - 50px);
  cursor: default;
  margin: auto;
  overflow-x: hidden;
  overflow-y: auto;
}

.album_list::-webkit-scrollbar{
  display: none;
}

.album_list_box {
  width: 100%;
  display: flex;
  flex-wrap: wrap;
  justify-content: space-around;
}

.album_box {
  width: 200px;
  height: 180px;
  background-color: rgba(10, 10, 10, 0.7);
  border-radius: 10px;
  margin: 10px 5px;
  display: block;
  float: left;
  transition: all 0.3s ease;
}

.album_box:hover {
  color: white;
  box-shadow: inset 0px 5px 25px gainsboro;
  transition: all 0.3s ease;
}

.last_line{
  width: 200px;
  height: 180px;
  margin: 10px 5px;
}

@media only screen and (max-width: 850px) {
  .album_list {
    width: 100%;
  }
  .last_line,
  .album_box {
    width: 150px;
    height: 120px;
  }
}
</style>
