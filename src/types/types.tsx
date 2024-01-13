export interface ITask {
  id: string | number[];
  title: string;
  description: string;
  status: boolean;
  fileName: string | null;
  uri: string | null;
}
