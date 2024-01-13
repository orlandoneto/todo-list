import If from "../../dist/If";
import { ITask } from "../../types/types";
import { getStatusLabel } from "../../utils/helper";
import {
  Container,
  ItemContainer,
  Title,
  Info,
  Status,
  ImageContent,
  InfoContainer,
} from "./styles";
import React, { useEffect, useState } from "react";
import { FlatList, Image } from "react-native";

interface TaskListProps {
  tasks: ITask[];
}

interface TaskItemProps {
  task: ITask;
}

export const TaskList = ({ tasks }: TaskListProps) => {
  const [filteredTasks, setFilteredTasks] = useState<ITask[]>(tasks);

  useEffect(() => {
    setFilteredTasks(tasks);
  }, [tasks]);

  const TaskItem = ({ task }: TaskItemProps) => {
    return (
      <Container>
        <ItemContainer>
          <InfoContainer>
            <Title>{task?.title}</Title>
            <Info>{task?.description}</Info>
            <Status status={task?.status}>
              {getStatusLabel(task?.status)}
            </Status>
          </InfoContainer>
          <ImageContent>
            <Image
              style={{ width: 100, height: 100 }}
              source={{ uri: `data:image/png;base64,${task?.uri}` }}
              resizeMode="contain"
            />
            <Info>{task?.fileName}</Info>
          </ImageContent>
        </ItemContainer>
      </Container>
    );
  };

  return (
    <Container>
      <If condition={tasks.length > 0}>
        <>
          <FlatList
            data={filteredTasks}
            keyExtractor={(item) => item.id.toString()}
            renderItem={({ item }) => <TaskItem task={item} />}
          />
        </>
      </If>
    </Container>
  );
};
