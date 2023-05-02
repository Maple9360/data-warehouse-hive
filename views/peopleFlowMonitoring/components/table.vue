<template>
  <div class="tableWrapper">
    <el-input
      v-model="keyword"
      class="w-50 m-2 inputStyle"
      placeholder="Empno UserName"
      :prefix-icon="Search"
    />
    <el-table :data="tableDataFilter" style="min-height: 25vh">
      <el-table-column
        v-for="info in props.tableHeader"
        :key="info.key"
        :label="info.label"
        :property="info.key"
      />
    </el-table>
    <div class="paginationStyle">
      <el-pagination
        v-model:current-page="currentPage"
        v-model:page-size="pageSize"
        :page-sizes="[5, 10, 50, 100]"
        :small="small"
        :disabled="disabled"
        :background="false"
        layout="total, sizes, prev, pager, next, jumper"
        :total="tableDataFilter.length"
        @size-change="handleSizeChange"
        @current-change="handleCurrentChange"
      />
      <!-- :total="tableDataFilter.length" -->
    </div>
  </div>
</template>

<script setup lang="ts">
import { computed, ref, watch } from 'vue'
import { Search } from '@element-plus/icons-vue'
const keyword = ref()
const props = defineProps({
  empno: {
    type: String,
    default: '',
  },
  tableHeader: {
    type: Array,
    default: () => [],
  },
  tableData: {
    type: Array,
    default: () => [],
  },
})
// =========================== 模糊搜索 ====================================
const tableDataFilter = computed(() => {
  const start = pageSize.value * (currentPage.value - 1)
  const end = currentPage.value * pageSize.value
  if (keyword.value) {
    const arr = props.tableData.filter((obj) => {
      return (
        obj.empno.indexOf(keyword.value) !== -1 ||
        obj.username.indexOf(keyword.value) !== -1
      )
    })
    // return arr 此时arr为筛选后的数组，再进行一次分页,分页后的数组为arr2
    return arr.slice(start, end)
  } else {
    return props.tableData.slice(start, end)
  }
})
// =========================== pagination页码 ==============================
const pageSize = ref(5)
const currentPage = ref(1)
const handleSizeChange = (size) => {
  // 数据条数改变时
  pageSize.value = size
}
</script>
<style lang="scss" scoped>
.tableWrapper :deep() {
  .el-table {
    color: #fff;
    background: transparent;
    // border: 1px solid green !important;
    --el-table-border-color: none;
    --el-table-row-hover-bg-color: transparent;
    & .cell {
      // 控制表格单元表格
      word-break: normal;
    }
    & thead {
      // 表头样式
      color: rgb(255, 255, 255);
      background: #222;
      tr {
        background-image: url('/src/assets/authority/tableItemBg.png'); // thead的样式
        background-size: 100% 100%;
      }
    }
    & tbody {
      tr:nth-child(2n) {
        background-image: url('/src/assets/authority/tableItemBg.png'); // tbody单独设置tr的样式
        background-size: 100% 100%;
      }
    }
    .el-table__cell {
      background: transparent;
      //   border-color: #555;
      border: none;
      padding: 5px 3px;
      font-size: 14px;
    }
    @media screen and (max-width: 1200px) {
      .el-table__cell {
        font-size: 12px;
      }
    }
    tr {
      background: transparent;
      //   &:nth-child(1) { 不能在这里写，thead的tr和td是分开计数的
      //     background: red;
      //   }
    }
    .el-table-fixed-column--right {
      // 右侧固定的
      // background: #22222288; // 优先级高于thead
    }
  }
  .el-checkbox__input.is-checked .el-checkbox__inner {
    // background-image: url('/src/assets/authority/yes.png');
    // background-size: 100% 100%;
    // background: transparent;
    background-color: #222;
    border-color: $font-gray;
  }
  .el-checkbox__input.is-indeterminate .el-checkbox__inner {
    background-color: $font-gray;
    border-color: $font-gray;
  }

  // input
  .inputStyle {
    width: 200px;
    margin: 10px 0px;
    float: right;
    background: transparent;
  }
  .el-input__wrapper {
    background-color: transparent;
    box-shadow: 0 0 0 1px $font-gray;
    --el-input-placeholder-color: #79aaa7;
  }
  .el-input__inner {
    color: $font-color;
  }
  .el-input__prefix {
    color: $font-color;
  }
}
.paginationStyle :deep() {
  .el-pagination {
    float: right;
    & button {
      background-color: transparent;
    }
  }
  .el-pager li {
    background: transparent;
    color: $font-gray;
    &.is-active {
      color: $font-color;
    }
  }
  .el-pagination__total {
    color: $font-gray;
  }
  .el-pagination__jump {
    color: $font-gray;
  }
  //   0 0 0 1px #ec2711 inset
  .el-select:hover:not(.el-select--disabled) .el-input__wrapper {
    box-shadow: 0 0 0 1px $font-color inset;
  }
}
</style>
