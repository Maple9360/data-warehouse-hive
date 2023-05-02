<template>
  <div v-if="!props.item.hidden">
    <template
      v-if="
        hasOneShowingChild(props.item.children, props.item) &&
        (!onlyOneChild.children || onlyOneChild.noShowingChildren) &&
        !props.item.alwaysShow
      "
    >
      <!-- 有一个显示的孩子 && （onlyOneChild没有下阶的孩子 || onlyOneChild有孩子但是不显示） && 当前父菜单不显示 =====> 代表妻其中没有嵌套的路由需要显示，是路由递归的最底层 -->
      <app-link v-if="onlyOneChild.meta" :to="resolvePath(onlyOneChild.path)">
        <!-- 给item加一层链接 -->
        <el-menu-item
          :index="resolvePath(onlyOneChild.path)"
          :class="{
            titleStyle: onlyOneChild.meta.title === 'title',
            right: props.item.meta.location === 'right',
            personStyle: props.item.meta.title === 'setting',
          }"
        >
          <item
            v-if="onlyOneChild.meta"
            :title="onlyOneChild.meta.title"
          ></item>
        </el-menu-item>
      </app-link>
    </template>

    <el-sub-menu
      v-else
      ref="submenu"
      :index="resolvePath(props.item.path)"
      :class="{
        right: props.item.meta.location === 'right',
        personStyle: props.item.meta.title === 'setting',
      }"
    >
      <template #title>
        <item v-if="props.item.meta" :title="props.item.meta.title"></item>
      </template>
      <sidebar-item
        v-for="child in props.item.children"
        :key="child.path"
        :item="child"
        :base-path="resolvePath(child.path)"
      />
    </el-sub-menu>
  </div>
</template>

<script setup lang="ts">
import { ref } from 'vue'
import Item from './item.vue'
import appLink from './link.vue'
import path from 'path-browserify'
const props = defineProps({
  item: Object,
  isNest: Boolean,
  basePath: String,
})
let onlyOneChild = ref(null)
const hasOneShowingChild = (
  children: Array<string> = [],
  parent: any
): boolean => {
  const showingChildren = children.filter((item) => {
    if (item?.hidden) {
      // hidden为true就隐藏起来
      return false
    } else {
      onlyOneChild.value = item // 把没隐藏的孩子放在onlyOneChild中存储，为showingChildren.length === 1时做准备（多个会覆盖，但是return false，也就是不生效；如果是单个，会暂存下来，后面返回true就会生效）
      // console.log('未隐藏的：', item)
      return true
    }
  })
  // ↑ 筛选出 hidden不为true的 子菜单的数组
  // ↓ 判断数量
  if (showingChildren.length === 1) {
    // 子菜单只有一个
    return true
  }
  if (showingChildren.length === 0) {
    // 子菜单无，我们创建一个
    onlyOneChild.value = { ...parent, path: '', noShowingChildren: true }
    return true
  }
  return false // child > 1时，抛出
}

const resolvePath = (routePath: any) => {
  // console.log(props.basePath, ' + ', routePath)
  return path.resolve(props.basePath, routePath)
  // 拼接
}
</script>

<style lang="scss" scoped>
.el-menu--horizontal {
  // 这里不生效，下面的子选择器生效
  // justify-content: flex-start;
  // background: transparent;
  // bottom: 0px;
  // position: relative;
  // top: 5vh;
  .el-item {
    background-color: #000;
  }
  .el-menu-item {
    max-width: 7vw !important;
    font-size: 12px;
    color: $font-gray;
    height: 4vh;
    min-height: 30px;
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
      padding: 0px;
      max-width: 20vw !important;
      background: #000;
      // flex: 2;
      &:hover {
        background-color: transparent !important;
        // background: #000;
        background-image: linear-gradient(to right, #000, rgb(179, 179, 179) 50%, #000) !important;
      }
      &.is-active {
        background: #000;
      }
    }
    &.personStyle { // 当setting菜单下只有一项时
      max-width: 20vw !important;
      background: #000 !important;
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
    max-width: 9vw !important;
    height: 4vh;
    min-height: 30px;
    flex: 1;
    background-image: url('/src/assets/background/unselect.png');
    background-size: 100% 100%;
    // border: 1px solid green;
    :deep().el-sub-menu__title {
      height: 4vh;
      min-height: 30px;
      font-size: 12px;
      color: $font-gray;
      &:hover {
        background-color: transparent !important;
        color: $font-color !important;
      }
    }
    &.titleStyle {
      max-width: 20vw;
      background: rgb(0, 0, 0);
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
    &.personStyle { // 当setting菜单下只有一项时
      max-width: 20vw !important;
      min-width: 9vw;
      background: #000;
      &:hover {
        background-color: transparent !important;
        background: #000;
      }
      &.is-active {
        background: #000;
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
