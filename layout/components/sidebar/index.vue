<template>
  <!-- 上标题部分是写死的，暂时 -->
  <div class="sidebar-container">
    <div class="navbar">
      <div class="tagStyle">
        <el-popover placement="right" trigger="hover">
          <template #reference>
            <svg-icon className="iconMenu" iconName="icon-language4"></svg-icon>
          </template>
          <ul @click="changeLang($event)">
            <li :class="{ isActive: lang === 'zh' }">简体中文</li>
            <li :class="{ isActive: lang === 'zhtw' }">繁体中文</li>
            <li :class="{ isActive: lang === 'en' }">English</li>
          </ul>
        </el-popover>

        <!-- <svg-icon className="iconMenu" iconName="icon-language2"></svg-icon> -->
        <svg-icon className="iconMenu" iconName="icon-email-fill"></svg-icon>
      </div>
      <el-menu
        :default-active="activeIndex"
        class="el-menu-demo"
        :ellipsis="false"
        mode="horizontal"
        @select="handleSelect"
      >
        <sidebar-item
          v-for="route in constantRoutes"
          :item="route"
          :key="route.path"
          :base-path="route.path"
        ></sidebar-item>
      </el-menu>
    </div>
  </div>
</template>

<script setup lang="ts">
import { ref } from 'vue'
import { constantRoutes } from '@/router/index.ts'
import sidebarItem from './sidebarItem.vue'
import { useConfigStore } from '@/store/modules/config.ts'
const config = useConfigStore()
const handleSelect = (p) => {
  console.log('当前路由: ', p)
}
// 多语言切换
let lang = ref('zh')
lang.value = localStorage.getItem('LANG')
const changeLang = (e) => {
  // console.log(e.target.innerHTML)
  const langSelect = e.target.innerHTML
  switch (langSelect) {
    case '简体中文':
      lang.value = 'zh'
      break
    case '繁体中文':
      lang.value = 'zhtw'
      break
    case 'English':
      lang.value = 'en'
      break
  }
  config.SET_LANG(lang.value)
}
</script>

<style lang="scss" scoped>
.el-menu--horizontal {
  // 这里生效
  // right: 2.5vw;
  justify-content: center;
  font-size: 12px;
  background: transparent;
  bottom: 0px;
  position: relative;
  top: 5vh;
  min-height: 30px;
  // .el-item {
  //   background-color: #000;
  // }
  // .el-menu-item {
  //   max-width: 8vw !important;
  //   font-size: 12px;
  //   color: $font-gray;
  //   height: 4vh;
  //   min-height: 30px;
  //   background-image: url('/src/assets/background/unselect.png');
  //   background-size: 100% 100%;
  //   background-repeat: no-repeat;
  //   // border: 1px solid red;
  //   &.right {
  //     background-image: url('/src/assets/background/right-unselect.png');
  //     background-size: 100% 100%;
  //     &.is-active {
  //       // background: transparent;
  //       background-image: url('/src/assets/background/right-selected.png') !important;
  //       background-color: #000; // is-active时的底色
  //     }
  //     &:hover {
  //       background-image: url('/src/assets/background/right-selected.png');
  //     }
  //   }
  //   &.titleStyle {
  //     max-width: 20vw !important;
  //     background: #000;
  //     // flex: 2;
  //     &:hover {
  //       background-color: transparent !important;
  //       background: #000;
  //     }
  //     &.is-active {
  //       background: #000;
  //     }
  //   }
  //   &.is-active {
  //     background-image: url('/src/assets/background/selected.png');
  //     background-color: #000 !important; // is-active时的底色

  //     color: $font-color !important;
  //   }
  //   &:hover {
  //     background-color: transparent !important;
  //     background-image: url('/src/assets/background/selected.png');

  //     color: $font-color !important;
  //   }
  // }
  // .el-sub-menu {
  //   max-width: 10vw !important;
  //   height: 4vh;
  //   flex: 1;
  //   background-image: url('/src/assets/background/unselect.png');
  //   background-size: 100% 100%;
  //   // border: 1px solid green;
  //   :deep().el-sub-menu__title {
  //     font-size: 12px;
  //     color: $font-gray;
  //     &:hover {
  //       background-color: transparent !important;
  //       color: $font-color !important;
  //     }
  //   }
  //   &.titleStyle {
  //     max-width: 20vw;
  //     background: #000;
  //     // flex: 2;
  //     &:hover {
  //       background-color: transparent !important;
  //       background: #000;
  //     }
  //     &.is-active {
  //       background: #000;
  //     }
  //   }
  //   &:hover {
  //     background-color: transparent !important;
  //     background-image: url('/src/assets/background/selected.png');
  //   }
  //   &.right {
  //     background-image: url('/src/assets/background/right-unselect.png');
  //     background-size: 100% 100%;
  //     &.is-active {
  //       // background: transparent;
  //       background-image: url('/src/assets/background/right-selected.png') !important;
  //       background-color: #000; // is-active时的底色
  //     }
  //     &:hover {
  //       background-image: url('/src/assets/background/right-selected.png');
  //     }
  //   }
  //   &.is-active {
  //     background-color: transparent !important;
  //     background-image: url('/src/assets/background/selected.png');
  //     :deep().el-sub-menu__title {
  //       color: $font-color !important;
  //     }
  //   }
  // }
}
.el-menu--popup-container {
  .el-menu--popup {
    background-color: rgb(133, 43, 21) !important;
    border: none;
    .el-menu-item {
      max-width: 18vw !important;
      color: $font-gray !important;
      background: #000;
      &:hover {
        background-color: $bg-gray !important; // 下阶hover颜色
        color: $font-color !important;
      }
      &.is-active {
        color: $font-color !important;
        background: $bg-gray !important;
      }
    }
    .el-sub-menu {
      background: #000;
      :deep().el-sub-menu__title {
        background: #000;
        &:hover {
          background-color: green !important; // 下阶hover颜色
        }
      }
    }
  }

  // .el-menu .el-menu--popup .el-menu--popup-bottom-start {
  //   background: #ff0 !important;
  //   background-color: #ff0 !important;
  // }
}

// 上方的小标签
.tagStyle {
  position: absolute;
  right: 0px;
  // background: lightblue;
  height: 30px;
  width: 200px;
  margin: 5px;
  display: flex;
  justify-content: flex-end;
  align-items: center;
}
.iconMenu {
  color: $font-gray;
  font-size: 24px;
  margin: 0px 5px;
  &:hover {
    font-size: 26px;
    color: $font-color;
  }
}

ul {
  padding: 5px 0;
  margin: 0;
}
li {
  font-size: 14px;
  list-style: none;
  padding: 2px 10px 0px 10px;
  margin: 0;
  &:hover {
    cursor: pointer;
    color: $font-color;
    background: #ddd;
  }
}
.isActive {
  color: $font-color;
}
</style>

<style lang="scss">
.el-popper.is-light {
  // 下拉框框
  border: 1px solid $font-color !important;
}
.el-menu--popup {
  // 下拉弹窗
  min-width: fit-content !important;
  background-color: rgb(0, 0, 0) !important;
}

// 多语言弹窗
.el-popover.el-popper {
  min-width: fit-content !important;
  max-width: fit-content;
  color: $font-gray !important;
  padding: 0 !important;
  // background: #000 !important;
  // background: transparent !important;
}
.el-popper.is-light .el-popper__arrow::before {
  // border: 1px solid #000;
}
</style>
