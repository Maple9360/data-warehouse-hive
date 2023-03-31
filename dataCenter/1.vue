<template>
  <el-table :data="tableData">
    <el-table-column prop="name" label="姓名"></el-table-column>
    <el-table-column prop="age" label="年龄">
      <template #default="{ row }">
        <el-input v-model="row.age" type="number"></el-input>
      </template>
    </el-table-column>
    <el-table-column prop="gender" label="性别">
      <template #default="{ row }">
        <el-select v-model="row.gender">
          <el-option label="男" value="male"></el-option>
          <el-option label="女" value="female"></el-option>
        </el-select>
      </template>
    </el-table-column>
  </el-table>
</template>

<script lang='ts' setup>
import { ref } from 'vue'
import { TableDataItem } from './types'

const tableData = ref<TableDataItem[]>([
  { name: '张三', age: 18, gender: 'male' },
  { name: '李四', age: 20, gender: 'female' },
  { name: '王五', age: 22, gender: 'male' },
])
</script>
