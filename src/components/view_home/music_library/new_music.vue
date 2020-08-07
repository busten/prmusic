<template>
  <div class="new_music">
    <ul class="line_box">
      <li v-for="(item,index) in music" :key="index">
        <div class="coverimg">
          <img src="" :onerror="defaultImg">
        </div>
        <div class="content">
          <div>歌曲名称<span>-歌手</span><span class="times">00:00</span></div>
          <div>合辑名称</div>
        </div>
      </li>
    </ul>
  </div>
</template>

<script scoped>
export default {
    data(){
        return{
            defaultImg: 'this.src="' + require('../../../assets/image/default.jpg') + '"',
            music: 20,
        }
    }
}
</script>

<style scoped>
.new_music{
    width: 100%;
    height: 100%;
}

.line_box li{
  width: 100%;
  height: 50px;
  background-color: rgba(10,10,10,0.3);
  cursor: default;
}

.line_box li:hover{
  background-color: rgba(184, 184, 184,0.3);
}

.coverimg{
  display: inline-block;
  width: 70px;
  height: 50px;
  margin-left: 5px;
}

.coverimg img{
  display: inline-block;
  width: 100%;
  height: 40px;
  border-radius: 5px;
  margin: 5px 0;
}

.content{
  width: calc(100% - 90px);
  display: inline-block;
  margin-bottom: 5px;
  margin-left: 10px;
}

.times{
  float: right;
  margin-top: 10px;
}
</style>
