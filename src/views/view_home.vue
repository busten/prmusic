<template>
  <div class="home">
    <div class="backgrounds_home">
      <div class="centel">
        <div ref="header" class="header">
          <div class="logo"></div>
          <ul>
            <li :class="{setcolor:component_name == 'homepage'}" @click="check_homepage">首页</li>
            <li
              :class="{setcolor:component_name == 'music_library'}"
              @click="check_music_library"
            >音乐库</li>
            <li :class="{setcolor:component_name == 'discover'}" @click="check_discover">我的收藏</li>
            <li :class="{setcolor:component_name == 'search'}" @click="check_search">
              <SvgIcon class="svgclas" name="search" />搜索
            </li>
          </ul>
          <div @click="changeinpersonal" class="users"></div>
        </div>
        <div :style="{height:content_height}" class="content">
          <transition name="fade" mode="out-in">
            <component
              :check_back="check_back"
              @getmusic="getmusic"
              @getalbum="getalbum"
              :is="component_name"
            />
          </transition>
        </div>
        <player :setalbum="setalbum" :setdata="setdata" style="z-index: 10" />
      </div>
    </div>
  </div>
</template>
<script>
import homepage from "../components/view_home/homepage";
import music_library from "../components/view_home/music_library";
import discover from "../components/view_home/discover";
import search from "../components/view_home/search";
import player from "../components/player";
import user from "../components/personal";
export default {
  components: {
    homepage,
    music_library,
    discover,
    search,
    player,
    user,
  },
  data() {
    return {
      content_height: 0,
      component_name: "homepage",
      setdata: "",
      setalbum: "",
      check_back: false,
    };
  },
  methods: {
    getalbum(obj) {
      this.setalbum = obj;
      setTimeout(() => {
        this.setalbum = "";
      }, 500);
    },
    getmusic(datas) {
      this.setdata = datas;
    },
    getcontent_height() {
      let we = this.$refs.header.offsetHeight;
      var bodys = document.body.clientWidth;
      if(bodys > 850 && bodys < 861){
        we += 25;
      }
      this.content_height = "calc(100% - " + (we + 70) + "px)";
    },
    check_homepage() {
      this.component_name = "homepage";
      this.check_back = !this.check_back;
    },
    check_music_library() {
      this.component_name = "music_library";
      this.check_back = !this.check_back;
    },
    check_discover() {
      this.component_name = "discover";
      this.check_back = !this.check_back;
    },
    check_search() {
      this.component_name = "search";
      this.check_back = !this.check_back;
    },
    changeinpersonal() {
      this.component_name = "user";
    },
  },
  mounted() {
    this.getcontent_height();
    window.addEventListener("resize", this.getcontent_height);
  },
  destroyed() {
    window.removeEventListener("resize", this.getcontent_height);
  },
};
</script>

<style>
.svgclas {
  padding-right: 10px;
  vertical-align: bottom;
}

.home {
  width: 100%;
  height: 100%;
}

.backgrounds_home {
  width: 100%;
  height: 100%;
  background: url("../assets/background/home.jpg");
  background-repeat: no-repeat;
  background-position: center;
  background-size: cover;
  position: absolute;
  z-index: 1;
}

.backgrounds_home::after {
  content: "";
  position: absolute;
  height: 60%;
  width: 100%;
  top: 0;
  background-image: linear-gradient(to bottom, black, transparent);
  z-index: 1;
}

.backgrounds_home::before {
  content: "";
  position: absolute;
  height: 60%;
  width: 100%;
  background-image: linear-gradient(to top, black, transparent);
  bottom: 0;
  z-index: 1;
}

.centel {
  width: 100%;
  height: 100%;
  position: relative;
  z-index: 2;
}

.header {
  position: relative;
  width: 100%;
  padding-bottom: 10px;
  text-align: center;
}

.logo {
  width: 216px;
  height: 51px;
  float: left;
  background: url("../assets/background/logo.png");
  background-repeat: no-repeat;
  background-position: center;
  background-size: cover;
  vertical-align: middle;
  margin: auto;
}

.header ul {
  display: inline-block;
  color: gainsboro;
  margin-right: 210px;
  cursor: default;
}

.header ul li {
  display: inline-block;
  padding: 0px 20px;
  line-height: 40px;
  text-align: center;
  margin-top: 5px;
  font-size: 18px;
  font-weight: bold;
  transition: all 0.3s ease;
}

.header ul li:hover {
  color: white;
  transition: all 0.3s ease;
}

.users {
  width: 30px;
  height: 30px;
  border-radius: 50%;
  background-color: white;
  float: right;
  margin-right: 20px;
  margin-top: 5px;
}

.content {
  width: 100%;
  color: white;
  overflow: auto;
}

.content::-webkit-scrollbar {
  display: none;
}

.fade-enter-active {
  transition: all 0.3s ease;
}
.fade-leave-active {
  transition: all 0.3s cubic-bezier(1, 0.5, 0.8, 1);
}
.fade-enter,
.fade-leave-to {
  transform: translateY(100px);
  opacity: 0;
}

.setcolor {
  color: white;
}

@media only screen and (max-width: 850px) {
  .header ul {
    margin-right: 0px;
  }
  .logo {
    float: inherit;
  }
  .users {
    position: absolute;
    right: 0;
    margin-right: 0px;
  }
}
</style>
