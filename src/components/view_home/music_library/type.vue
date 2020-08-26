<template>
  <div class="fortype">
    <div class="labels">
      <ul class="labels_box">
        <li @mouseleave="moveoutli" @mouseenter="moveli(item)" v-for="item in lable" :key="item">
          <span>坂本真绫</span>
          <div :style="{'background-color':labelcolor[item]}" class="label1"></div>
          <div
            :style="{'background-color':labelcolor[item]}"
            class="label2"
            :class="{ismove:ismove == item}"
          ></div>
        </li>
      </ul>
      <div class="labels_presentation">
        <Presentation />
      </div>
    </div>
  </div>
</template>

<script>
import Presentation from "../../view_home/music_library/presentation";
export default {
  components: {
    Presentation,
  },
  data() {
    return {
      ismove: "",
      lable: 100,
      labelcolor: [],
    };
  },
  methods: {
    moveli(e) {
      this.ismove = e;
    },
    moveoutli() {
      this.ismove = "";
    },
    setcolor() {
      let r, g, b;
      for (let index = 0; index <= this.lable; index++) {
        r = Math.floor(Math.random() * 256);
        g = Math.floor(Math.random() * 256);
        b = Math.floor(Math.random() * 256);
        this.labelcolor.push("rgb(" + r + "," + g + "," + b + ")");
      }
    },
  },
  mounted() {
    this.setcolor();
  },
};
</script>

<style scoped>
.fortype {
  width: 100%;
  height: 100%;
}

.labels {
  width: 100%;
  height: 100%;
}

.labels_box {
  width: 100%;
  height: 100px;
  background-color: rgba(10, 10, 10, 0.5);
  border-bottom: 1px solid gainsboro;
  overflow-x: hidden;
  overflow-y: auto;
  cursor: pointer;
}

.labels_box::-webkit-scrollbar {
  display: none;
}

.labels_box li {
  width: 100px;
  height: 25px;
  border: 1px solid white;
  border-radius: 0px 5px 5px 0px;
  margin: 10px;
  position: relative;
  padding-left: 10px;
  float: left;
  transition: all 0.3s ease;
}

.labels_box li:hover {
  -webkit-text-stroke: 1px white;
  color: transparent;
  transition: all 0.3s ease;
}

.labels_box li span {
  position: absolute;
  z-index: 1;
}

.label1 {
  position: absolute;
  width: 5px;
  height: 100%;
  left: 0;
  top: 0;
}

.label2 {
  position: absolute;
  width: 0;
  height: 100%;
  left: 0;
  top: 0;
  transition: all 1s ease;
}

.ismove {
  width: 100%;
  transition: all 0.3s ease;
}

.labels_presentation {
  width: 100%;
  height: calc(100% - 110px);
  overflow: auto;
}

.labels_presentation::-webkit-scrollbar{
  display: none;
}
</style>
