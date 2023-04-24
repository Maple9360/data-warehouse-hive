<template>
  <div>
    <el-select v-model="selectedFields" multiple filterable>
      <el-option v-for="field in fields" :key="field" :label="field" :value="field" :disabled="selectedFields.indexOf(field) < 0">
      </el-option>
    </el-select>
  </div>
</template>

<script lang="ts" setup>
import { ref, onMounted } from 'vue';
import { ElSelect, ElOption } from 'element-plus';

const fields = ref<string[]>([]);
const selectedFields = ref<string[]>([]);

onMounted(() => {
  // 请求后端获取所有表的字段和数据，并更新fields数组
  fetch('/api/fields')
    .then(response => response.json())
    .then(data => {
      fields.value = data.fields;
    })
    .catch(error => {
      console.error(error);
    });
});

// 导出组件
export default {
  components: { ElSelect, ElOption },
  setup() {
    return {
      fields,
      selectedFields,
    };
  },
};
</script>
