<template>
  <div class="app2-wrapper">
    <div class="page-wrapper">
      <div>
        <el-tabs
          v-model="activeName"
          class="demo-tabs"
          @tab-click="handleClick"
        >
          <!-- :label="$t('test')" -->
          <el-tab-pane name="first">
            <template #label>
              <tab-pane>
                <svg-icon
                  className="iconMenu"
                  iconName="icon-apply2"
                ></svg-icon>
                {{ $t('authority.signup') }}
              </tab-pane>
            </template>

            <div style="padding: 20px">
              <apply-form action="#"></apply-form>
            </div>
            <!-- <sign-up></sign-up> -->
            <!-- 申请模块 -->
          </el-tab-pane>
          <el-tab-pane
            v-if="role === 'admin'"
            name="second"
            style="padding: 20px"
          >
            <template #label>
              <tab-pane>
                <svg-icon className="iconMenu" iconName="icon-edit"></svg-icon>
                {{ $t('authority.tobesign') }}
              </tab-pane>
            </template>
            <multi-table v-if="activeName === 'second'"></multi-table>
          </el-tab-pane>
          <el-tab-pane name="third">
            <template #label>
              <tab-pane>
                <svg-icon
                  className="iconMenu"
                  iconName="icon-gongzuorizhi"
                ></svg-icon>
                {{ $t('authority.changelog') }}
              </tab-pane>
            </template>
            <permission-log></permission-log>
          </el-tab-pane>
          <!-- <el-tab-pane
            v-if="role === 'admin' ? true : false"
            label="Config"
            name="fourth"
            >config</el-tab-pane
          > -->
        </el-tabs>
        <div @click="backTo" class="button-style">
          <!-- <img
          class="img-style"
          src="/src/assets/authority/download.png"
          alt=""
          style="height: 30px; transform: rotate(90deg)"
        /> -->
          <img
            class="img-style"
            style="height: 30px"
            src="/src/assets/total/back.png"
          />
        </div>
      </div>
    </div>
  </div>
</template>

<script setup lang="ts">
import applyForm from './component/form.vue'
import signUp from './component/signUp.vue'
import multiTable from './component/multiTable.vue'
import permissionLog from './component/permissionLog.vue'
import type { TabsPaneContext } from 'element-plus'
import { reactive, ref } from 'vue'
import { useUserStore } from '@/store/modules/user.ts'
import { getUserInfo } from '@/api/login.ts'
import { useRouter } from 'vue-router'
// api
import { getDropdown } from '@/api/authority.ts'
const router = useRouter()

// ============== 标签页切换 =================
const handleClick = (tab: TabsPaneContext, event: Event) => {
  console.log('标签页切换：', tab, event, activeName.value)
}
const activeName = ref('first')
const role = ref('admin')
// ================ apply ==================
const personInfo = {
  id: {
    value: 'X2000957',
    title: '—— Employee Number ——',
  },
  name: {
    value: 'HHHHHHH',
    title: '—— User ——',
  },
  email: {
    value: 'nsdii-@mail.foxconn.com',
    title: '—— E-mail ——',
  },
  department: {
    value: '资讯管理处',
    title: '—— Department ——',
  },
  area: {
    value: 'FOL',
    title: '—— Area ——',
  },
  permission: {
    // 目前权限
    value: 'Guest',
    title: '—— Present Permission ——',
  },
}
const permissionList = [
  {
    value: 'Admin',
    label: '管理員',
  },
  {
    value: 'Guest',
    label: '訪客',
  },
  {
    value: 'NWE',
    label: 'NWE',
  },
  {
    value: 'NSD',
    label: 'NSD',
  },
]
const requestData = {
  id: '',
  permissionApply: '',
  signStatus: '',
  idSigned: '',
}
// 批量注冊文件上传
// ================ back ===================
const backTo = () => {
  router.push({
    path: '/dashboard/index',
  })
}

const getData = () => {
  getUserInfo().then((res) => {
    // console.log(res)
  })
  getDropdown().then((res) => {
    console.log(res)
  })
}
getData()
</script>

<style lang="scss" scoped>
// 根组件下第一层
.app2-wrapper {
  width: 100%;
  height: 100%;
  background: rgb(0, 0, 0);
  // background-size: 100% 100%;
  position: relative;
}
.page-wrapper {
  color: $font-gray;
  background: #000;
  background-image: url('/src/assets/background/bg.jpg');
  background-size: 100% 100%;
  min-height: 100vh;
  // position: relative;
  padding: 45px;
  box-sizing: border-box;
  & :deep() .el-tabs__item.is-active {
    color: $font-color;
    background-image: url('/src/assets/authority/selected.png');
    //   background-size: 100% 100%;
  }
  & :deep() .el-tabs__item {
    background-image: url('/src/assets/authority/unselected.png');
    background-size: 100% 100%;
    text-align: center;
    color: $font-gray;
    width: 200px;
    &:hover {
      color: $font-color;
    }
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
// 标签
.tag-container {
  max-width: 90%; // 按照比例
  height: 40px;
  border: 1px solid white;
  margin: 0 5px;
  box-shadow: 3px 3px 5px #2e2e2e;
  border-radius: 5px;
}
.el-check-tag {
  // background-color: #4c5e78;
  background-color: #276c52;
  color: #fff;
  margin: 5px;
}
.el-check-tag.is-checked {
  background-color: #000735;
  color: #fffb82;
}
.apply-style {
  background: transparent;
  // width: 100%;
  // margin: 10px auto;
  .el-button {
    display: block;
    border: none;
    background: rgb(78, 156, 137);
    color: #fff;
    margin: auto;
    border-radius: 5px;
    width: 100px;
    height: 25px;
  }
}

// icon
.iconMenu {
  color: $font-color !important;
  font-size: 20px;
  margin: 0px 5px;
}
</style>
