<template>
  <div class="search">
    <div v-show="!iscomponent" class="search_inpit">
      <input v-on:keyup.enter="submit" :class="{havedata:datas != 0}" class="forinput" type="text" name="search" />
      <div class="icon_search">
        <SvgIcon name="search" />
      </div>
      <div class="ending">
        <ul>
          <li v-for="index in datas" :key="index">搜索结果<SvgIcon class="icons" name="search" /></li>
        </ul>
      </div>
    </div>
    <component v-show="iscomponent" :is="comName"/>
  </div>
</template>

<script>
import search_result from '../view_home/search/search_result';
export default {
  components:{
    search_result,
  },
  data(){
    return{
      datas: 0,
      comName: '',
      iscomponent: false,
    }
  },
  methods:{
    submit(){
      this.comName = 'search_result';
      this.iscomponent = !this.iscomponent;
    }
  }
}
</script>

<style scoped>
.search {
  width: 100%;
  height: 100%;
  display: flex;
  justify-content: center;
  align-items: center;
}

.search_inpit {
  width: 30%;
  height: 70%;
  margin-top: 10%;
  min-width: 280px;
  position: relative;
  margin-right: 20px;
}

.forinput {
  width: 100%;
  padding: 5px;
  padding-right: 20px;
  border-radius: 5px 5px 5px 5px;
  outline: none;
  border: none;
}

.havedata{
  border-radius: 5px 5px 0px 0px;
}

.icon_search {
  position: absolute;
  color: black;
  top: 2px;
  right: -20px;
}

.ending {
  width: 100%;
  height: 70%;
  padding-right: 25px;
  color: black;
  overflow-y: auto;
  overflow-x: hidden;
}

.ending::-webkit-scrollbar{
  display: none;
}

.ending ul{
  width: 100%;
  background-color: gainsboro;
}

.ending ul li {
  width: 100%;
  padding: 5px;
  padding-right: 20px;
  background-color: white;
}

.ending ul li:hover {
  background-color: gainsboro;
}

.icons{
  float: right;
  opacity: 0.5;
}
</style>
