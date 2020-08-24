<template>
  <div class="homepage">
    <div class="homepage_header">
      <p>信息一览</p>
    </div>
    <div class="homepage_box">
      <div class="homepage_box_header">
        <div class="list_box list_box2">
          <span class="data_overview">数据总览</span>
          <div ref="echarts_pie" class="echarts_pie"></div>
        </div>
        <div class="list_box list_box1">
          <p>今日歌曲播放量</p>
          <p class="list_box_datas">120,305</p>
        </div>
        <div class="list_box list_box1">
          <p>网站浏览量</p>
          <p class="list_box_datas">120,305</p>
        </div>
        <div class="list_box list_box1">
          <p>用户注册量</p>
          <p class="list_box_datas">120,305</p>
        </div>
        <div class="list_box list_box1">
          <p>歌曲数量</p>
          <p class="list_box_datas">120,305</p>
        </div>
        <div class="list_box list_box1">
          <p>合辑数量</p>
          <p class="list_box_datas">120,305</p>
        </div>
      </div>
      <div class="homepage_box_centel">
        <div ref="echarts_bar" class="echarts_bar"></div>
        <div ref="echarts_line" class="echarts_line"></div>
      </div>
    </div>
  </div>
</template>

<script>
let echarts = require("echarts/lib/echarts");
require("echarts/lib/chart/pie");
require("echarts/lib/chart/bar");
require("echarts/lib/chart/line");
// 引入提示框和title组件
require("echarts/lib/component/title");
require("echarts/lib/component/tooltip");
export default {
  methods: {
    initecharts_pie() {
      let cahart = this.$refs.echarts_pie;
      let myChart = echarts.init(cahart);
      myChart.resize();
      myChart.setOption({
        tooltip: {
          trigger: "item",
          backgroundColor: "rgba(45, 80, 177,0.6)",
          formatter: "{a} <br/>{b} : {c} ({d}%)",
        },
        toolbox: {
          show: true,
          feature: {
            mark: { show: true },
            dataView: { show: true, readOnly: false },
            magicType: {
              show: true,
              type: ["pie", "funnel"],
            },
            restore: { show: true },
            saveAsImage: { show: true },
          },
        },
        series: [
          {
            name: "数据总览",
            type: "pie",
            label: {
              show: false,
            },
            data: [
              { value: 10, name: "今日播放量" },
              { value: 10, name: "网站浏览量" },
              { value: 5, name: "用户注册量" },
              { value: 20, name: "歌曲数量" },
              { value: 18, name: "合辑数量" },
            ],
          },
        ],
      });
    },
    initecharts_bar() {
      let cahart = this.$refs.echarts_bar;
      let myChart = echarts.init(cahart);
      myChart.resize();
      myChart.setOption({
        color: ["#3398DB"],
        title: {
          text: "网站浏览历史(周)",
          left: "center",
          textStyle: {
            color: "white",
          },
        },
        tooltip: {
          trigger: "axis",
          backgroundColor: "rgba(45, 80, 177,0.6)",
          axisPointer: {
            // 坐标轴指示器，坐标轴触发有效
            type: "shadow", // 默认为直线，可选为：'line' | 'shadow'
          },
        },
        grid: {
          left: "3%",
          right: "4%",
          bottom: "3%",
          containLabel: true,
        },
        xAxis: [
          {
            type: "category",
            data: ["周一", "周二", "周三", "周四", "周五", "周六", "周日"],
            axisTick: {
              alignWithLabel: true,
            },
            axisLine: {
              lineStyle: {
                color: "white",
              },
            },
          },
        ],
        yAxis: [
          {
            type: "value",
            axisLine: {
              lineStyle: {
                color: "white",
              },
            },
          },
        ],
        series: [
          {
            name: "直接访问",
            type: "bar",
            barWidth: "60%",
            data: [10, 52, 200, 334, 390, 330, 220],
          },
        ],
      });
    },
    initecharts_line() {
      var date = new Date();
      var year = date.getFullYear(); //获取完整的年份(4位,1970-????)
      var month = date.getMonth() + 1; //获取当前月份(0-11,0代表1月)
      var day = new Date(year, month, 0);
      var daycount = day.getDate();
      var mon = [];
      var med = [];
      for (let index = 1; index <= daycount; index++) {
        mon.push(index);
        med.push((Math.random()*100).toFixed(0));
      }
      let cahart = this.$refs.echarts_line;
      let myChart = echarts.init(cahart);
      myChart.resize();
      myChart.setOption({
        color: ["#3398DB"],
        title: {
          text: "网站浏览历史(月)",
          left: "center",
          textStyle: {
            color: "white",
          },
        },
        tooltip: {
          trigger: "item",
          backgroundColor: "rgba(45, 80, 177,0.6)",
          axisPointer: {
            // 坐标轴指示器，坐标轴触发有效
            type: "shadow", // 默认为直线，可选为：'line' | 'shadow'
          },
        },
        xAxis: {
          type: "category",
          data: mon,
          axisLine: {
            lineStyle: {
              color: "white",
            },
          },
        },
        yAxis: {
          type: "value",
          axisLine: {
            lineStyle: {
              color: "white",
            },
          },
        },
        series: [
          {
            data: med,
            type: "line",
          },
        ],
      });
    },
  },
  mounted() {
    this.initecharts_pie();
    this.initecharts_bar();
    this.initecharts_line();
    window.addEventListener("resize", this.initecharts_bar);
    window.addEventListener("resize", this.initecharts_line);
  },
  destroyed(){
    window.removeEventListener("resize", this.initecharts_bar);
    window.removeEventListener("resize", this.initecharts_line);
  }
};
</script>

<style scoped>
.homepage {
  width: 100%;
  height: 100%;
}

.homepage_header {
  width: 100%;
  height: 50px;
  text-align: center;
  background-color: rgb(3, 33, 114);
  box-shadow: 0px 1px 20px 1px rgb(45, 80, 177);
}

.homepage_header p {
  font-size: 20px;
  font-weight: bold;
  padding-top: 10px;
}

.homepage_box {
  width: 80%;
  height: calc(100% - 90px);
  margin-top: 20px;
  margin: 20px auto;
  overflow-x: hidden;
  overflow-y: auto;
}

.homepage_box::-webkit-scrollbar {
  display: none;
}

.homepage_box_header {
  width: 100%;
  font-weight: bold;
  display: flex;
  flex-wrap: wrap;
  justify-content: space-around;
}

.homepage_box_header p {
  font-size: 20px;
  padding-top: 5px;
}

.list_box {
  background-color: rgb(3, 33, 114);
  margin: 30px 10px;
  box-shadow: 0 0 20px 5px rgb(45, 80, 177);
  float: left;
}

.list_box_datas {
  margin-top: 20px;
}

.list_box1 {
  width: 200px;
  height: 100px;
  text-align: center;
}

.list_box2 {
  width: 300px;
  height: 100px;
}

.data_overview {
  float: left;
  margin-left: 10px;
  line-height: 100px;
  font-size: 30px;
  text-align: center;
}

.echarts_pie {
  width: 100px;
  height: 100px;
  float: right;
}

.homepage_box_centel {
  width: 100%;
}

.echarts_bar {
  width: calc(50% - 20px);
  height: 650px;
  background-color: rgb(3, 33, 114);
  box-shadow: 0 0 20px 5px rgb(45, 80, 177);
  margin-top: 10px;
  margin-left: 20px;
  margin-bottom: 20px;
  float: left;
}

.echarts_line {
  width: calc(50% - 60px);
  height: 650px;
  background-color: rgb(3, 33, 114);
  box-shadow: 0 0 20px 5px rgb(45, 80, 177);
  margin-top: 10px;
  margin-left: 40px;
  margin-bottom: 20px;
  float: left;
}

@media only screen and (max-width: 850px) {
  .homepage_box {
    width: 100%;
  }
  .list_box2 {
    width: 100%;
  }
  .echarts_bar {
    width: calc(100% - 40px);
  }

  .echarts_line {
    width: calc(100% - 40px);
    margin-left: 20px;
  }
}
</style>
