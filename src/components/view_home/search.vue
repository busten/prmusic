<template>
  <div class="search">
    <div v-show="!iscomponent" class="search_inpit">
      <input
        v-on:keyup.enter="submit"
        :class="{havedata:datas != 0}"
        v-model="searchname"
        class="forinput"
        type="text"
        name="search"
        autocomplete="off"
      />
      <div class="icon_search">
        <SvgIcon name="search" />
      </div>
      <div class="ending">
        <ul>
          <li @click="checkdatas(index)" v-for="index in datas" :key="index">
            {{index}}
            <div class="datasearchicon">
              <SvgIcon class="icons" name="search" />
            </div>
          </li>
        </ul>
      </div>
    </div>
    <component @backsearch="backsearch" v-show="iscomponent" :is="comName" />
  </div>
</template>

<script>
import search_result from "../view_home/search/search_result";
export default {
  props: {
    check_back: Boolean,
  },
  components: {
    search_result,
  },
  data() {
    return {
      datas: 0,
      comName: "",
      iscomponent: false,
      searchname: "",
    };
  },
  methods: {
    submit() {
      this.comName = "search_result";
      this.iscomponent = !this.iscomponent;
    },
    backsearch() {
      this.comName = "";
      this.iscomponent = !this.iscomponent;
    },
    checkdatas(obj){
      this.searchname = obj;
    }
  },
  watch: {
    check_back() {
      this.comName = "";
      this.iscomponent = false;
    },
    searchname(obj) {
      if (obj != "") {
        this.$fetchPost("/prmusic/user/search", {
          objname: obj,
        }).then((res) => {
          this.datas = res.message;
        });
      } else {
        this.datas = 0;
      }
    },
  },
};
</script>

<style scoped>
.search {
  width: 100%;
  height: 100%;
  display: flex;
  justify-content: center;
  align-items: center;
  cursor: default;
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
  color: black;
  outline: none;
  border: none;
}

.havedata {
  border-radius: 5px 5px 0px 0px;
}

.icon_search {
  position: absolute;
  color: black;
  top: 5px;
  right: -20px;
}

.ending {
  width: calc(100% + 25px);
  height: 70%;
  color: black;
  overflow-y: auto;
  overflow-x: hidden;
}

.ending::-webkit-scrollbar {
  display: none;
}

.ending ul {
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

.icons {
  float: right;
  opacity: 0.5;
}

.datasearchicon{
  float: right;
  margin-right: 10px;
}
</style>
