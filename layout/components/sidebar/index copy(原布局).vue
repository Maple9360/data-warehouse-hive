<template>
  <!-- 上标题部分是写死的，暂时 -->
  <div class="sidebar-container">
    <div class="navbar">
      <el-menu
        :default-active="activeIndex"
        class="el-menu-demo"
        :ellipsis="false"
        mode="horizontal"
        @select="handleSelect"
      >
        <el-menu-item index="1">Home</el-menu-item>
        <el-menu-item index="2">Alarm Center</el-menu-item>
        <el-menu-item index="3">Data Center</el-menu-item>
        <el-sub-menu popper-class="pop-style" index="4">
          <template #title>Grouding Mgmt</template>
          <el-menu-item index="4-1">楼栋接地</el-menu-item>
          <el-menu-item index="4-2">楼层接地</el-menu-item>
          <el-menu-item index="4-3">线体接地</el-menu-item>
          <el-menu-item index="4-4">工站接地</el-menu-item>
        </el-sub-menu>
        <el-sub-menu popper-class="pop-style" index="5">
          <template #title>Personal Mgmt</template>
          <el-menu-item index="5-1">人员接地</el-menu-item>
          <el-menu-item index="5-2">人体行走</el-menu-item>
        </el-sub-menu>

        <el-menu-item class="titleStyle" index="11"
          ><img
            style="width: 20vw"
            src="/src/assets/background/title.png"
            alt=""
        /></el-menu-item>
        <el-sub-menu popper-class="pop-style" class="right" index="6"
          ><template #title>ESDS Mgmt</template>
          <el-menu-item index="6-1">周期性检验物品</el-menu-item>
          <el-menu-item index="6-2">防静电物品进料检验</el-menu-item>
          <el-menu-item index="6-3">防静电物品认证认可</el-menu-item>
        </el-sub-menu>

        <el-menu-item class="right" index="7">EOS Mgmt</el-menu-item>
        <el-menu-item class="right" index="8">ESD Audit</el-menu-item>
        <el-menu-item class="right" index="9">Sharing</el-menu-item>
        <el-sub-menu class="titleStyle" index="12">
          <template #title>
            <div style="line-height: 4vh">
              <img
                style="height: 4vh"
                src="/src/assets/background/person.png"
                alt=""
              />
            </div>
          </template>
          <el-menu-item index="12-1">Account Mgmt</el-menu-item>
          <el-menu-item index="12-2">防静电物品进料检验</el-menu-item>
          <el-menu-item index="12-3">防静电物品认证认可</el-menu-item>
        </el-sub-menu>
      </el-menu>
    </div>
  </div>
</template>

<script setup lang="ts">
const handleSelect = (p) => {
  console.log(p)
}
</script>

<style lang="scss" scoped>
.el-menu--horizontal {
  justify-content: center;
  background: transparent;
  bottom: 0px;
  position: relative;
  top: 5vh;
  .el-item {
    background-color: #000;
  }
  .el-menu-item {
    max-width: 8vw !important;
    font-size: 12px;
    color: $font-gray;
    height: 4vh;
    background-image: url('/src/assets/background/unselect.png');
    background-size: 100% 100%;
    background-repeat: no-repeat;
    // border: 1px solid red;
    &.right {
      background-image: url('/src/assets/background/right-unselect.png');
      background-size: 100% 100%;
      &.is-active {
        // background: transparent;
        background-image: url('/src/assets/background/right-selected.png') !important;
        background-color: #000; // is-active时的底色
      }
      &:hover {
        background-image: url('/src/assets/background/right-selected.png');
      }
    }
    &.titleStyle {
      max-width: 20vw !important;
      background: #000;
      // flex: 2;
      &:hover {
        background-color: transparent !important;
        background: #000;
      }
      &.is-active {
        background: #000;
      }
    }
    &.is-active {
      background-image: url('/src/assets/background/selected.png');
      background-color: #000 !important; // is-active时的底色

      color: $font-color !important;
    }
    &:hover {
      background-color: transparent !important;
      background-image: url('/src/assets/background/selected.png');

      color: $font-color !important;
    }
  }
  .el-sub-menu {
    max-width: 10vw !important;
    height: 4vh;
    flex: 1;
    background-image: url('/src/assets/background/unselect.png');
    background-size: 100% 100%;
    // border: 1px solid green;
    :deep().el-sub-menu__title {
      font-size: 12px;
      color: $font-gray;
      &:hover {
        background-color: transparent !important;
        color: $font-color !important;
      }
    }
    &.titleStyle {
      max-width: 20vw;
      background: #000;
      // flex: 2;
      &:hover {
        background-color: transparent !important;
        background: #000;
      }
      &.is-active {
        background: #000;
      }
    }
    &:hover {
      background-color: transparent !important;
      background-image: url('/src/assets/background/selected.png');
    }
    &.right {
      background-image: url('/src/assets/background/right-unselect.png');
      background-size: 100% 100%;
      &.is-active {
        // background: transparent;
        background-image: url('/src/assets/background/right-selected.png') !important;
        background-color: #000; // is-active时的底色
      }
      &:hover {
        background-image: url('/src/assets/background/right-selected.png');
      }
    }
    &.is-active {
      background-color: transparent !important;
      background-image: url('/src/assets/background/selected.png');
      :deep().el-sub-menu__title {
        color: $font-color !important;
      }
    }
  }
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
</style>
