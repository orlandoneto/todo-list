module.exports = {
    transformer: {
        assetPlugins: ['react-native-svg-asset-plugin'],
        svgAssetPlugin: {
            pngCacheDir: '.png-cache',
            scales: [1, 2, 3],
            output: {
                compressionLevel: 9,
            },
        },
    },
    resolver: { extensions: ['.js', '.ts'] },
};
