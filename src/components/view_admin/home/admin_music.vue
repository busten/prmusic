<template>
  <div class="admin_music">
    <div class="admin_music_header">
      <p>音乐歌曲管理页</p>
    </div>
    <div class="admin_music_centel">

    </div>
  </div>
</template>

<script>
export default {};
</script>

<style>
.admin_music {
  width: 100%;
  height: 100%;
}

.admin_music_header {
  width: 100%;
  height: 50px;
  text-align: center;
  background-color: rgb(3, 33, 114);
  box-shadow: 0px 1px 20px 1px rgb(45, 80, 177);
}

.admin_music_header p {
  font-size: 20px;
  font-weight: bold;
  padding-top: 10px;
}

.admin_music_centel{
  width: 80%;
  height: calc(100% - 80px);
  margin: auto;
  margin-top: 20px;
  background-color: antiquewhite;
}

@media only screen and (max-width: 850px) {
.admin_music_centel{
  width: 100%;
}
}
</style>
