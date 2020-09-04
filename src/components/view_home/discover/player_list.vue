<template>
  <div class="player_list">
    <ul v-show="close_box" ref="getboxwidth" class="label_centent">
      <li ref="getliwidth" @click="add_play_list" class="label_centent_li addlist">
        <SvgIcon class="icon" name="add" />
        <p>添加列表</p>
      </li>
      <li @click="change_music_list" class="label_centent_li" v-for="iten in album" :key="iten"></li>
      <li class="last_line" v-for="item in complement" :key="item+'a'"></li>
      <div :class="{close_window:is_close_window}" class="window">
        <p>列表名称</p>
        <input type="text" />
        <div class="btn">
          <button @click="close_window" style="margin-right: 10px;">取消</button>
          <button @click="form_data">确定</button>
        </div>
      </div>
    </ul>
    <div :class="{close_mu_list:close_mu_list}" class="mu_list">
      <component :is="comName" />
    </div>
  </div>
</template>

<script>
import presentation from "../music_library/presentation";
export default {
  props:{
    backpage:{type: Boolean}
  },
  components: {
    presentation,
  },
  data() {
    return {
      album: 11,
      complement: 0,
      is_close_window: true,
      comName: "",
      close_box: true,
      close_mu_list: true,
    };
  },
  methods: {
    change_music_list() {
      window.removeEventListener("resize", this.set_complement);
      this.close_mu_list = false;
      this.close_box = false;
      this.comName = "presentation";
      this.$emit('inplaylist',true);
    },
    add_play_list() {
      this.is_close_window = false;
    },
    close_window() {
      this.is_close_window = true;
    },
    form_data() {
      this.is_close_window = true;
    },
    set_complement() {
      let getboxwidth = this.$refs.getboxwidth.clientWidth;
      let getliwidth = this.$refs.getliwidth.clientWidth + 10;
      let columns = parseInt(getboxwidth / getliwidth);
      let nums = this.album / columns;
      let rows =
        this.album % columns == 0 ? nums : parseInt(this.album / columns) + 1;
      this.complement = columns * rows - this.album - 1;
    },
  },
  watch:{
    backpage:function(){
      this.close_box = true;
      this.comName = '';
    }
  },
  mounted() {
    this.set_complement();
    window.addEventListener("resize", this.set_complement);
  },
  destroyed() {
    this.$emit('inplaylist',false);
    window.removeEventListener("resize", this.set_complement);
  },
};
</script>

<style scoped>
.player_list {
  width: 100%;
  height: calc(100% - 50px);
}
.label_centent {
  width: 80%;
  margin: auto;
  overflow-x: hidden;
  overflow-y: auto;
  display: flex;
  flex-wrap: wrap;
  justify-content: space-around;
}

.label_centent::-webkit-scrollbar {
  display: none;
}

.label_centent_li {
  width: 200px;
  height: 180px;
  background-color: rgba(10, 10, 10, 0.7);
  border-radius: 10px;
  margin: 10px 5px;
  display: block;
  float: left;
  transition: all 0.3s ease;
}

.label_centent_li:hover {
  color: white;
  box-shadow: inset 0px 5px 25px gainsboro;
  transition: all 0.3s ease;
}

.last_line {
  width: 200px;
  height: 180px;
  margin: 10px 5px;
}

.icon {
  margin-top: 30%;
  font-size: 50px;
}

.addlist {
  text-align: center;
  color: gray;
}

.window {
  position: absolute;
  width: 300px;
  height: 200px;
  background-color: rgba(10, 10, 10, 0.9);
  margin: auto;
  margin-top: 10%;
  text-align: center;
}

.window p {
  width: 80%;
  margin: 0 auto;
  padding-top: 10%;
}

.window input {
  outline: none;
  border: none;
  border-radius: 5px;
  line-height: 20px;
  width: 80%;
  padding-left: 5px;
  color: black;
}

.btn {
  width: 100%;
  position: absolute;
  bottom: 0;
  display: flex;
  justify-content: flex-end;
}

.btn button {
  outline: none;
  border: none;
  padding: 5px 10px;
  background-color: gray;
  transition: all 0.3s ease;
}

.btn button:hover {
  background-color: white;
  color: black;
  transition: all 0.3s ease;
}

.close_window {
  display: none;
}

.mu_list {
  width: 80%;
  height: 100%;
  overflow-x: hidden;
  overflow-y: auto;
  margin: auto;
}

.close_mu_list {
  display: none;
}

.mu_list::-webkit-scrollbar {
  display: none;
}

@media only screen and (max-width: 850px) {
  .label_centent,
  .mu_list {
    width: 100%;
  }
  .last_line,
  .label_centent li {
    width: 150px;
    height: 120px;
  }
  .icon {
    margin-top: 23%;
  }
}
</style>
