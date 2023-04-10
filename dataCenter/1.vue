<template>
  <div>
    <el-form ref="form" :model="form" label-width="100px">
      <el-form-item v-for="(item, index) in formItems" :key="index" :label="item.label">
        <el-input v-if="item.type === 'input'" v-model="form[item.key]" />
        <el-select v-else-if="item.type === 'select'" v-model="form[item.key]">
          <el-option v-for="(option, i) in item.options" :key="i" :label="option.label" :value="option.value" />
        </el-select>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" @click="submitForm">提交</el-button>
        <el-button @click="resetForm">重置</el-button>
      </el-form-item>
    </el-form>
  </div>
</template>

<script lang="ts">
import { defineComponent, reactive } from 'vue'
import { ElForm, ElFormItem, ElInput, ElSelect, ElOption, ElButton } from 'element-plus'

export default defineComponent({
  name: 'FormDemo',
  components: {
    ElForm,
    ElFormItem,
    ElInput,
    ElSelect,
    ElOption,
    ElButton
  },
  setup() {
    const form = reactive({
      name: '',
      gender: '',
      age: ''
    })

    const formItems = [
      {
        label: '姓名',
        key: 'name',
        type: 'input'
      },
      {
        label: '性别',
        key: 'gender',
        type: 'select',
        options: [
          {
            label: '男',
            value: 'male'
          },
          {
            label: '女',
            value: 'female'
          }
        ]
      },
      {
        label: '年龄',
        key: 'age',
        type: 'input'
      }
    ]

    function submitForm() {
      console.log(form)
    }

    function resetForm() {
      Object.keys(form).forEach(key => {
        form[key] = ''
      })
    }

    return {
      form,
      formItems,
      submitForm,
      resetForm
    }
  }
})
</script>
