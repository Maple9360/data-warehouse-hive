<template>
  <div>
    <el-table :data="tableData1" ref="table1">
      <el-table-column prop="name" label="姓名"></el-table-column>
      <el-table-column prop="age" label="年龄"></el-table-column>
      <el-table-column prop="email" label="邮箱"></el-table-column>
    </el-table>

    <button @click="downloadExcel(tableData1)">下载 Excel 文件1</button>

    <el-table :data="tableData2" ref="table2">
      <el-table-column prop="name" label="姓名"></el-table-column>
      <el-table-column prop="age" label="年龄"></el-table-column>
      <el-table-column prop="email" label="邮箱"></el-table-column>
    </el-table>

    <button @click="downloadExcel(tableData2)">下载 Excel 文件2</button>

    <el-table :data="tableData3" ref="table3">
      <el-table-column prop="name" label="姓名"></el-table-column>
      <el-table-column prop="age" label="年龄"></el-table-column>
      <el-table-column prop="email" label="邮箱"></el-table-column>
    </el-table>

    <button @click="downloadExcel(tableData3)">下载 Excel 文件3</button>
  </div>
</template>

<script lang="ts" setup>
import { ref } from 'vue';
import * as XLSX from 'xlsx';

const tableData1 = [...];
const tableData2 = [...];
const tableData3 = [...];

const tableRef1 = ref<HTMLElement | null>(null);
const tableRef2 = ref<HTMLElement | null>(null);
const tableRef3 = ref<HTMLElement | null>(null);

const downloadExcel = (tableData: any[]) => {
  let tableRef: any;

  if (tableData === tableData1) {
    tableRef = tableRef1;
  } else if (tableData === tableData2) {
    tableRef = tableRef2;
  } else if (tableData === tableData3) {
    tableRef = tableRef3;
  }

  const table = tableRef.value?.querySelector('.el-table__body');

  if (table) {
    // 从表格中获取数据
    const data = [];
    const rows = table.querySelectorAll('.el-table__row');
    rows.forEach((row) => {
      const rowData = [];
      const cells = row.querySelectorAll('.el-table__cell');
      cells.forEach((cell) => {
        rowData.push(cell.textContent);
      });
      data.push(rowData);
    });

    // 将数据转换为 Excel 文件
    const workbook = XLSX.utils.book_new();
    const sheet = XLSX.utils.aoa_to_sheet(data);
    XLSX.utils.book_append_sheet(workbook, sheet, 'Sheet1');
    const excelBuffer = XLSX.write(workbook, { bookType: 'xlsx', type: 'array' });
    const blob = new Blob([excelBuffer], { type: 'application/vnd.openxmlformats-officedocument.spreadsheetml.sheet' });
    const fileName = 'table.xlsx';

    // 下载文件
    const link = document.createElement('a');
    link.href = URL.createObjectURL(blob);
    link.download = fileName;
    document.body.appendChild(link);
    link.click();
    document.body.removeChild(link);
  }
};
</
