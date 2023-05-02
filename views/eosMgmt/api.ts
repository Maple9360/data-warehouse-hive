// 接口格式
// 热力图
tableData = [
  {
    mfr: 'AAA', // 第一项是名称,要求string
    '2c': { history: 0, current: 1 }, // 要求object 或 0; 当history，current都为0时，可以返回数字 0，也可以返回 { history: 0, current: 1 }
    '4c': 0, // 等同于{ history: 0, current: 0 }
    bft: { history: 5, current: 1 },
    'bst/pt': { history: 5, current: 1 },
    fst: { history: 5, current: 1 },
    ict: { history: 5, current: 1 },
    jtag: { history: 5, current: 1 },
    p2c: { history: 5, current: 1 },
    p2c2c: { history: 5, current: 1 },
    pcbvf: { history: 5, current: 1 },
    runin: { history: 5, current: 1 },
  },
  {
    mfr: 'BBBB',
    '2c': { history: 0, current: 1 },
    '4c': { history: 5, current: 1 },
    bft: { history: 5, current: 1 },
    'bst/pt': { history: 5, current: 1 },
    fst: { history: 5, current: 1 },
    ict: { history: 5, current: 1 },
    jtag: { history: 5, current: 1 },
    p2c: { history: 5, current: 1 },
    p2c2c: { history: 5, current: 1 },
    pcbvf: { history: 5, current: 1 },
    runin: { history: 5, current: 1 },
  },
]
