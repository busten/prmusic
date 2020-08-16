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
  pluginOptions: {
    svgLoader: {
      svgo: {
        plugins: []
      }
    }
  },
}
