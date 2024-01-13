import { Container, ItemText, ListItem } from "./style";
import { useNavigation } from "@react-navigation/native";
import { StackNavigationProp } from "@react-navigation/stack";
import React from "react";

type RootStackParamList = {
  Task: undefined;
  NewTask: undefined;
};

type HomeScreenNavigationProp = StackNavigationProp<
  RootStackParamList,
  "Task" | "NewTask"
>;

export const Home = () => {
  const navigation: HomeScreenNavigationProp = useNavigation();

  return (
    <Container>
      <>
        <ListItem onPress={() => navigation.navigate("Task")}>
          <ItemText>Tarefas</ItemText>
        </ListItem>

        <ListItem onPress={() => navigation.navigate("NewTask")}>
          <ItemText>Adicionar tarefa</ItemText>
        </ListItem>
      </>
    </Container>
  );
};
