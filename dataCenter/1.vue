<template>
  <el-select v-model="selectedOption" placeholder="请选择">
    <el-option v-for="option in options" :key="option.value" :label="option.label" :value="option.value"></el-option>
  </el-select>

  <el-table v-if="selectedOption === 'table1'" :data="table1Data">
    <!-- 表格列定义 -->
  </el-table>

  <el-table v-if="selectedOption === 'table2'" :data="table2Data">
    <!-- 表格列定义 -->
  </el-table>
</template>

<script setup lang="ts">
import { ref } from 'vue';
import { getTable1Data, getTable2Data } from '@/api';

interface Option {
  label: string;
  value: string;
}

const options: Option[] = [
  { label: '表格1', value: 'table1' },
  { label: '表格2', value: 'table2' },
];

const selectedOption = ref<string>(''); // 当前选中的选项
const table1Data = ref<any[]>([]);
const table2Data = ref<any[]>([]);

// 初始化数据
async function init() {
  table1Data.value = await getTable1Data();
  table2Data.value = await getTable2Data();
}

init();
</script>
