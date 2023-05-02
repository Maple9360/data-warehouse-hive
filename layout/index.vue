<template>
  <!-- app-wrapper -->
  <div class="app-wrapper">
    <!-- 上标题 -->
    <sidebar></sidebar>
    <!-- 下内容区 -->
    <app-main></app-main>
  </div>
</template>

<script lang="ts" setup>
import sidebar from '@/layout/components/sidebar/index.vue'
import appMain from './components/appMain.vue'
</script>
