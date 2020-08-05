<template>
  <div class="home">
    <div class="backgrounds_home">
      <div class="centel">
        <div ref="header" class="header">
          <div class="logo"></div>
          <ul>
            <li>首页</li>
            <li>音乐库</li>
            <li>发现</li>
            <li>
              <SvgIcon class="svgclas" name="search" />搜索
            </li>
          </ul>
        </div>
        <div :style="{height:content_height}" class="content">
        </div>
      </div>
    </div>
  </div>
</template>
<script>
export default {
  data() {
    return {
      content_height:0
    };
  },
  methods:{
    getcontent_height(){
      let we = this.$refs.header.offsetHeight;
      this.content_height = 'calc(100% - '+we+'px)'
    }
  },
  mounted(){
    this.getcontent_height()
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
  font-weight: 600;
  transition: all 0.3s ease;
}

.header ul li:hover {
  color: white;
  transition: all 0.3s ease;
}

.content {
  width: 100%;
  color: white;
  overflow: auto;
}

@media only screen and (max-width: 850px) {
  .header ul {
    margin-right: 0px;
  }
  .logo {
    float: inherit;
  }
}
</style>
