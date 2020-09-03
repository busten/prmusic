<template>
  <div class="album_particulars">
    <div class="album_img">
      <img :src="setalbum.img_url" :onerror="defaultImg" />
    </div>
    <div class="album_introduce">
      <table>
        <tr>
          <th colspan="3" class="album_introduce_title">{{setalbum.albumname}}</th>
        </tr>
        <tr>
          <td class="title_row">创建时间</td>
          <td>:</td>
          <td>{{setalbum.create_time}}</td>
        </tr>
        <tr>
          <td class="title_row">含有歌曲</td>
          <td>:</td>
          <td>{{setalbum.music_number == null ? 0:setalbum.music_number}}首</td>
        </tr>
        <tr valign="top" class="synopsis">
          <td class="title_row">简介</td>
          <td>:</td>
          <td class="synopsis_content">{{setalbum.introduce}}</td>
        </tr>
      </table>
    </div>
  </div>
</template>

<script>
export default {
  props: {
    setalbum: "",
  },
  data() {
    return {
      defaultImg:
        'this.src="' + require("../../../../assets/image/default.jpg") + '"',
    };
  },
};
</script>

<style scoped>
.album_particulars {
  width: 100%;
  height: 100%;
  overflow: hidden;
}

.album_img,
.album_introduce {
  display: inline-block;
}

.album_img {
  width: 30%;
  height: 100%;
  margin-left: 5px;
}

.album_img img {
  width: 90%;
  height: 90%;
  margin: 10px 10px 10px 0px;
  border-radius: 5px;
  box-shadow: 1px 0px 4px 3px black;
}

.album_introduce {
  width: 69%;
  height: 100%;
}

.album_introduce table {
  width: 100%;
  height: 100%;
  white-space: nowrap;
}

.title_row {
  width: 60px;
  text-align-last: justify;
}

.album_introduce_title {
  text-align: left;
  font-size: 20px;
  font-weight: bold;
}

.synopsis {
  width: 100%;
  height: 200px;
}

.synopsis_content {
  white-space: normal;
}

@media only screen and (max-width: 870px) {
  .album_img {
    display: none;
  }

  .album_introduce {
    width: 100%;
  }

  .album_introduce_title {
    text-align: center;
  }

  .synopsis {
    height: auto;
  }
}
</style>
