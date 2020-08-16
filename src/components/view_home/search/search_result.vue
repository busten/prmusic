<template>
  <div class="search_result">
    <p class="search_result_title">搜索结果</p>
    <div class="search_result_header">
      <p class="search_result_box_title">搜到音乐</p>
      <div class="result_box">
        <component :is="'presentation'" />
      </div>
    </div>
    <div class="search_result_centel">
      <p class="search_result_box_title">搜到合辑</p>
      <div class="result_box">
      </div>
    </div>
  </div>
</template>

<script>
import presentation from "../music_library/presentation";
import album from '../music_library/album';
export default {
  components: {
    presentation,
  },
  data() {
    return {};
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

.search_result_header {
  width: 100%;
  height: 40%;
}

.search_result_box_title {
  width: calc(100% - 10px);
  padding-left: 10px;
  padding-bottom: 5px;
}

.result_box {
  width: 100%;
  height: 100%;
  overflow-x: hidden;
  overflow-y: auto;
}

.result_box::-webkit-scrollbar {
  display: none;
}

.search_result_centel {
  width: 100%;
  height: 40%;
  margin-top: 30px;
}

@media only screen and (max-width: 850px) {
  .search_result {
    width: 100%;
  }
}
</style>
