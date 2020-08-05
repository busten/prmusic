// const path = require('path')
// function resolve(dir) {
//  return path.join(__dirname, '.', dir)
// }
module.exports = {
  devServer: {
    open: true,
    host: '192.168.1.10',
    https: false,
    port: 8081,
    proxy: {
      '/prmusic': {
        target: 'http://www.demo.com:8080/prmusic',
        ws: true,
        changOrigin: true,
        pathRewrite: {
          '^/prmusic': '/'
        }
      }
    }
  },
  // chainWebpack: config => {
  //   config.module
  //     .rule('svg')
  //     .exclude.add(resolve('src/icons'))
  //     .end()

  //   config.module
  //     .rule('icons')
  //     .test(/\.svg$/)
  //     .include.add(resolve('src/icons'))
  //     .end()
  //     .use('svg-sprite-loader')
  //     .loader('svg-sprite-loader')
  //     .options({
  //       symbolId: 'icon-[name]'
  //     })
  // },
  pluginOptions: {
    svgLoader: {
      svgo: {
        plugins: []
      }
    }
  }
}
