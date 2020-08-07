<template>
  <div class="home">
    <div class="backgrounds_home">
      <div class="centel">
        <div ref="header" class="header">
          <div class="logo"></div>
          <ul>
            <li @click="check_homepage">首页</li>
            <li @click="check_music_library">音乐库</li>
            <li>发现</li>
            <li>
              <SvgIcon class="svgclas" name="search" />搜索
            </li>
          </ul>
          <div class="users"></div>
        </div>
        <div :style="{height:content_height}" class="content">
          <transition name="fade" mode="out-in">
          <component :is="component_name"/>
          </transition>
        </div>
      </div>
    </div>
  </div>
</template>
<script>
import homepage from '../components/view_home/homepage';
import music_library from '../components/view_home/music_library';
export default {
  components:{
    homepage,music_library,
  },
  data() {
    return {
      content_height:0,
      component_name: 'homepage'
    };
  },
  methods:{
    getcontent_height(){
      let we = this.$refs.header.offsetHeight;
      this.content_height = 'calc(100% - '+we+'px)'
    },
    check_homepage(){
      this.component_name = 'homepage';
    },
    check_music_library(){
      this.component_name = 'music_library';
    }
  },
  mounted(){
    this.getcontent_height();
    window.addEventListener('resize', this.getcontent_height);
  },
  destroyed(){
    window.removeEventListener('resize', this.getcontent_height);
  }
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
  display: inline-block;
  width: 216px;
  height: 51px;
  float: left;
  background: url("../assets/background/logo.png");
  background-repeat: no-repeat;
  background-position: center;
  background-size: cover;
  vertical-align: middle;
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

.users{
  width: 30px;
  height: 30px;
  border-radius: 50%;
  background-color: white;
  display: inline-block;
  float: right;
  margin-right: 20px;
  margin-top: 5px;
}

.content {
  width: 100%;
  color: white;
  overflow: auto;
}

.content::-webkit-scrollbar{
  display: none;
}

.fade-enter-active {
  transition: all .3s ease;
}
.fade-leave-active {
  transition: all .3s cubic-bezier(1.0, 0.5, 0.8, 1.0);
}
.fade-enter, .fade-leave-to {
  transform: translateY(100px);
  opacity: 0;
}

@media only screen and (max-width: 850px) {
  .header ul {
    margin-right: 0px;
  }
  .logo {
    float: inherit;
  }
  .users{
    position: absolute;
    right: 0;
    margin-right: 0px;
  }
}
</style>
