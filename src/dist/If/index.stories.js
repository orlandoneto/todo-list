import { storiesOf } from '@storybook/react-native';
import ifMarkdown from './if.md';
storiesOf('If', module)
    .addParameters({
    readme: {
        content: ifMarkdown,
    },
    viewport: {
        defaultViewport: '',
    },
})
    .add('Example', () => { });
//# sourceMappingURL=index.stories.js.map