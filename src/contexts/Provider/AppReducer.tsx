import { ITask } from "../../types/types";
import { SET_NEW_TASK, SET_CLEAR_CONTEXT } from "./AppActions";
import AsyncStorage from "@react-native-async-storage/async-storage";

export type AppState = {
  tasks: ITask[];
};

export const initialState = {
  tasks: [],
};

export type AppAction =
  | { type: typeof SET_NEW_TASK; payload: { tasks: ITask[] } }
  | { type: typeof SET_CLEAR_CONTEXT };

export const AppReducer = (state: AppState, action: AppAction): AppState => {
  switch (action.type) {
    case SET_NEW_TASK:
      const newState = {
        ...state,
        tasks: action.payload.tasks,
      };

      saveTasksToLocalStorage(newState.tasks);

      return newState;

    case SET_CLEAR_CONTEXT:
      const clearedState = {
        ...state,
        ...initialState,
      };

      clearTasksFromLocalStorage();

      return clearedState;

    default:
      return state;
  }
};

const saveTasksToLocalStorage = async (tasks: ITask[]): Promise<void> => {
  try {
    const serializedTasks = JSON.stringify(tasks);
    await AsyncStorage.setItem("KEY_PERSIST_NEW_TASK", serializedTasks);
  } catch (error) {
    console.error("Error saving tasks to local storage:", error);
  }
};

const clearTasksFromLocalStorage = async (): Promise<void> => {
  try {
    await AsyncStorage.removeItem("KEY_PERSIST_NEW_TASK");
  } catch (error) {
    console.error("Error clearing tasks from local storage:", error);
  }
};
