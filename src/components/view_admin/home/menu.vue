<template>
  <div class="menu_box">
    <van-cell class="home_btn" @click="homepage" title="主页" icon="wap-home-o" />
    <van-collapse v-model="activeNames" accordion>
      <van-collapse-item icon="bag-o" title="资源管理">
        <van-cell class="home_btn" @click="admin_album" title="合辑管理" icon="coupon-o" />
        <van-cell class="home_btn" @click="admin_song" title="歌手管理" icon="award-o" />
        <van-cell class="home_btn" @click="admin_music" title="音乐管理" icon="music-o" />
      </van-collapse-item>
      <van-collapse-item icon="user-o" title="用户管理">
        <van-cell class="home_btn" @click="admin_account" title="账号管理" icon="user-o" />
        <van-cell class="home_btn" @click="admin_user_group" title="用户组管理" icon="friends-o" />
        <van-cell class="home_btn" @click="admin_auth" title="账号权限管理" icon="cluster-o" />
      </van-collapse-item>
    </van-collapse>
    <van-cell class="home_btn" title="注销" icon="brush-o" />
  </div>
</template>

<script>
import { Collapse, CollapseItem, Cell } from "vant";
export default {
  components: {
    [Collapse.name]: Collapse,
    [CollapseItem.name]: CollapseItem,
    [Cell.name]: Cell,
  },
  data() {
    return {
      activeNames: "",
    };
  },
  methods: {
    homepage(){
      this.$emit('comName','homepage');
    },
    admin_album() {
      this.$emit('comName','album_admin');
    },
    admin_song() {
      this.$emit('comName','song_admin');
    },
    admin_music() {
      this.$emit('comName','music_admin');
    },
    admin_account() {
      this.$emit('comName','account_admin');
    },
    admin_user_group() {
      this.$emit('comName','user_group_admin');
    },
    admin_auth() {
      this.$emit('comName','auth_admin');
    },
  },
};
</script>

<style>
a:focus,
input:focus,
p:focus,
div:focus {
  -webkit-tap-highlight-color: rgba(0, 0, 0, 0);
  -webkit-user-modify: read-only;
}
.menu_box {
  width: 300px;
  height: 100%;
}

.home_btn {
  cursor: pointer;
}

.van-hairline--top-bottom {
  border: none;
}

.van-hairline--top-bottom::after {
  border: none;
}

.van-collapse-item--border {
  border: none;
}

.van-collapse-item--border::after {
  border: none;
}

.van-cell {
  background-color: transparent;
  color: white;
  border: none;
}

.van-cell::after {
  border: none;
}

.van-cell:active {
  background-color: transparent;
}

.van-collapse-item__content {
  background-color: rgb(5, 19, 59);
}
</style>
