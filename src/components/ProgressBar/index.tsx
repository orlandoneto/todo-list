import React, { useEffect, useRef } from 'react';
import { Animated } from 'react-native';
import { Container, Label, ProgressBarContent } from './styles';

interface ProgressBarProps {
    width: number;
    progressColor: string;
    progressBorder: string;
    progressBgColor: string;
    progressInfoColor: string; 
    count?: number;
}

const ProgressBar = ({
    width,
    progressColor,
    progressBorder,
    progressBgColor,
    progressInfoColor,
    count = 50,
}: ProgressBarProps) => {
    const loaderValue = useRef(new Animated.Value(0)).current;

    const load = (count: number) => {
        Animated.timing(loaderValue, {
            toValue: count,
            duration: 500,
            useNativeDriver: true,
        }).start();
    };

    useEffect(() => {
        load(count);
    }, [count]);

    return (
        <Container>
            <ProgressBarContent
                width={width}
                progressBorder={progressBorder}
                progressBgColor={progressBgColor}
            >
                <Animated.View
                    style={{
                        backgroundColor: progressColor,
                        borderRadius: 100,
                        width: `${count}%`,
                    }}
                />
            </ProgressBarContent>
            <Label progressInfoColor={progressInfoColor}>
                Carregando {`${count}%`}
            </Label>
        </Container>
    );
};

export default ProgressBar;
