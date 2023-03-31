<template>
  <div>
    <el-table :data="tableData" style="width: 100%">
      <el-table-column prop="name" label="姓名"></el-table-column>
      <el-table-column prop="age" label="年龄"></el-table-column>
      <el-table-column label="操作">
        <template #default="{ row }">
          <el-button @click="handleEdit(row)">编辑</el-button>
        </template>
      </el-table-column>
    </el-table>
    <el-dialog :visible.sync="dialogVisible">
      <el-form ref="form" :model="form" label-width="80px">
        <el-form-item label="姓名">
          <el-input v-model="form.name"></el-input>
        </el-form-item>
        <el-form-item label="年龄">
          <el-input v-model.number="form.age"></el-input>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="dialogVisible = false">取 消</el-button>
        <el-button type="primary" @click="handleSave">确 定</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script lang='ts' setup>
import { ref } from 'vue';
import { ElTable, ElTableColumn, ElButton, ElDialog, ElForm, ElFormItem, ElInput } from 'element-plus';

interface TableDataItem {
  name: string;
  age: number;
}

const tableData = ref<TableDataItem[]>([
  { name: '张三', age: 18 },
  { name: '李四', age: 22 },
  { name: '王五', age: 25 },
]);

const form = ref<TableDataItem>({
  name: '',
  age: 0,
});

const dialogVisible = ref(false);

function handleEdit(row: TableDataItem) {
  form.value = { ...row };
  dialogVisible.value = true;
}

function handleSave() {
  const index = tableData.value.findIndex((item) => item.name === form.value.name);
  if (index > -1) {
    Object.assign(tableData.value[index], form.value);
  } else {
    tableData.value.push(form.value);
  }
  dialogVisible.value = false;
}
</script>

<style scoped>
.dialog-footer {
  text-align: right;
}
</style>
