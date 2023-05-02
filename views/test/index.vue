<template>
  <div class="app2-wrapper">
    <div class="pSt">
      <div
        style="
          height: 900px;
          width: 500px;
          box-sizing: border-box;
          background: lightblue;
          border: 1px solid red;
        "
      >
        233333333333333333333333333333333333
      </div>
    </div>
  </div>
</template>

<script setup lang="ts"></script>

<style lang="scss" scoped>
.app2-wrapper {
  width: 100%;
  height: 100%;
  background: rgb(0, 0, 0);
  // background-size: 100% 100%;
  position: relative;
}
.pSt {
  background-image: url('/src/assets/background/bg.jpg');
  border: 1px solid rgb(29, 247, 21);
  background-size: 100% 100%;
  box-sizing: border-box;
  //   margin-top: 10vh;
  padding: 20px 45px 45px 45px; // 不覆盖设计底图
  position: relative;
  // width: 100%; // 宽高都不设置才能被内容撑起来
  min-height: 100vh;
}
</style>
