module.exports = {
    presets: ['module:metro-react-native-babel-preset'],
    plugins: [
        [
            'module-resolver',
            {
                root: ['./src'],
                extensions: ['.js', '.jsx', '.tsx', '.ios.js', '.android.js'],
                alias: {
                    src: './src',
                    'assets': './src/assets',
                    'components': './src/components',
                    'configs': './src/configs',
                    'contexts': './src/contexts',
                    'hooks': './src/hooks',
                    'i18n': './src/i18n',
                    'mock': './src/mock',
                    'routes': './src/routes',
                    'screens': './src/screens',
                    'services': './src/services',
                    'styles': './src/styles',
                    'utils': './src/utils',
                },
            },
        ],
    ],
};
