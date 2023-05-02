<template>
  <div class="authority-wrapper">
    <div>
      <el-tabs v-model="activeName" class="demo-tabs" @tab-click="handleClick">
        <el-tab-pane label="Permission application" name="first">
          内容
        </el-tab-pane>
        <el-tab-pane label="To be signed off" name="second">Config</el-tab-pane>
        <el-tab-pane label="Role" name="third">Role</el-tab-pane>
        <el-tab-pane
          v-if="role === 'admin' ? true : false"
          label="Config"
          name="fourth"
          >config</el-tab-pane
        >
      </el-tabs>
      <div @click="backTo" class="button-style">
        <img
          class="img-style"
          src="/src/assets/authority/download.png"
          alt=""
          style="height: 30px; transform: rotate(90deg)"
        />
        <!-- <img style="margin-left:20px;width: 30px" src="/src/assets/authority/user.png"> -->
      </div>
    </div>
  </div>
</template>

<script setup lang="ts">
// import { ref } from 'vue'
import type { TabsPaneContext } from 'element-plus'
import { ref } from 'vue'
import { useUserStore } from '@/store/modules/user.ts'
import { getUserInfo } from '@/api/login.ts'
import { useRouter } from 'vue-router'
const router = useRouter()
const handleClick = (tab: TabsPaneContext, event: Event) => {
  console.log(tab, event)
}
const activeName = ref('')
const role = ref('admin')
const getData = () => {
  getUserInfo().then((res) => {
    console.log(res)
  })
}
getData()

// ================ back ===================
const backTo = () => {
  router.push({
    path: '/dashboard/index',
  })
}
</script>

<style lang="scss" scoped>
.authority-wrapper {
  color: #fff;
  background: #000;
  background-image: url('/src/assets/background/bg.jpg');
  background-size: 100% 100%;
  width: 100%;
  height: 100%;
  padding: 45px;
  box-sizing: border-box;
  & :deep() .el-tabs__item.is-active {
    background-image: url('/src/assets/authority/selected.png');
    //   background-size: 100% 100%;
  }
  & :deep() .el-tabs__item {
    background-image: url('/src/assets/authority/unselected.png');
    background-size: 100% 100%;
    text-align: center;
    color: #fff;
    width: 200px;
  }
}
.button-style {
  position: absolute;
  right: 45px;
  top: 45px;
}
.img-style {
  &:hover {
    height: 35px !important;
  }
}
</style>
