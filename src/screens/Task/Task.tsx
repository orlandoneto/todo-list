import { TaskList } from "../../components/Task";
import { AppContext } from "../../contexts/Provider/AppProvider";
import { Header } from "./styles";
import React, { useContext, useState, useEffect } from "react";
import SelectDropdown from "react-native-select-dropdown";
import FontAwesome from "react-native-vector-icons/FontAwesome";
import If from "src/dist/If";
import { ITask } from "src/types/types";

const filterOptions = ["Todos", "Ativo", "Inativo"];

export const Task = () => {
  const context = useContext(AppContext);

  if (!context) {
    return null;
  }

  const { state } = context;
  const [selectedFilter, setSelectedFilter] = useState<string>("Todos");
  const [filteredTasks, setFilteredTasks] = useState<ITask[]>([]);

  useEffect(() => {
    if (selectedFilter === "Todos") {
      setFilteredTasks(state.tasks);
    } else if (selectedFilter === "Ativo") {
      setFilteredTasks(state.tasks.filter((t) => t.status));
    } else if (selectedFilter === "Inativo") {
      setFilteredTasks(state.tasks.filter((t) => !t.status));
    }
  }, [selectedFilter, state]);

  return (
    <>
      <Header accessibilityLabel="header-id">Listagem de Tarefas</Header>
      <SelectDropdown
        data={filterOptions}
        defaultValue={"Todos"}
        onSelect={(selectedItem) => setSelectedFilter(selectedItem)}
        renderDropdownIcon={(isOpened) => (
          <FontAwesome
            name={isOpened ? "chevron-up" : "chevron-down"}
            color={"#444"}
            size={18}
          />
        )}
        dropdownIconPosition={"right"}
        buttonTextStyle={{ fontSize: 15, textAlign: "left" }}
      />
      <If condition={state.tasks.length > 0}>
        <TaskList tasks={filteredTasks} />
      </If>
    </>
  );
};
